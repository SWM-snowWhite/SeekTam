import React, { useEffect, useState } from 'react'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios'
import KeywordComponent from '../components/KeywordComponent'
import NavigatorExceptSearch from '../components/NavigatorExceptSearch'
import MallRanking from '../components/MallRanking'
import ViewsRanking from '../components/ViewsRanking'

import { FoodListType, SearchOptionType, SearchTitleType } from './Search'

export default function Main() {
	const [relatedFoodList, setRelatedFoodList] = useState([])
	const [searchOptions, setSearchOptions] = useState(searchOptionList)
	const [keyword, setKeyword] = useState<string>('')
	const [optionView, setOptionView] = useState<Boolean>(false)
	const [foodList, setFoodList] = useState<FoodListType>([])
	const [selectedKeyword, setSelectedKeyword] = useState('')
	const [focusedFoodIdx, setFocusedFoodIdx] = useState<number>(-1)
	const [isSearched, setIsSearched] = useState(false)
	const [selectedFoodIdx, setSelectedFoodIdx] = useState<number>(-1)
	const [comparisonList, setComparisonList] = useState<FoodListType>([])
	const [viewComparison, setViewComparison] = useState(false)
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	// 키워드가 초기화 될 경우 관련 검색어 초기화
	useEffect(() => {
		if (keyword === '') {
			setRelatedFoodList([])
			setFocusedFoodIdx(-1)
		} else {
			fetchKeywordSearch(keyword)
		}
	}, [keyword])

	// 푸드 리스트가 변경 시 관련 검색어, 키워드 초기화
	useEffect(() => {
		setRelatedFoodList([])
		setKeyword('')
	}, [foodList])

	const handleChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
		setKeyword(e.target.value)
	}

	const clearKeyword = () => {
		setKeyword('')
	}

	const fetchKeywordSearch = (keyword: string) => {
		if (keyword === '') return

		axios
			.get(`${SERVER_API_URL}/foods/search/syllable?keyword=${keyword}`, {
				withCredentials: true,
			})
			.then(res => {
				setRelatedFoodList(res.data)
			})
			.catch(err => {
				console.log('검색에 실패하였습니다.', err)
			})
	}

	const handleKeyUp = (e: React.KeyboardEvent<HTMLInputElement>) => {
		if (e.key === 'Enter') {
			if (relatedFoodList.length === 0) return
			if (focusedFoodIdx === -1) {
				fetchOptionKeywordSearch()
				return
			}
			fetchOptionKeywordSearch(relatedFoodList[focusedFoodIdx])
			return
		}

		if (e.key === 'ArrowUp') {
			if (relatedFoodList.length === 0) return
			if (focusedFoodIdx > -1 && focusedFoodIdx < relatedFoodList.length)
				setFocusedFoodIdx(focusedFoodIdx - 1)
		}

		if (e.key === 'ArrowDown') {
			if (relatedFoodList.length === 0) return
			if (focusedFoodIdx < relatedFoodList.length - 1)
				setFocusedFoodIdx(focusedFoodIdx + 1)
		}
	}

	const fetchOptionKeywordSearch = async (clicked?: string) => {
		if (!clicked && keyword === '') return

		const optionKeywordUrl = await makeOptionKeywordUrl(clicked)
		const foodName = clicked ? clicked : keyword

		setSelectedKeyword(foodName)

		try {
			const response = await axios.get(optionKeywordUrl, {
				withCredentials: true,
			})

			let fetchedFoodList = response.data.slice(0, 10)
			setFoodList(fetchedFoodList)
			setIsSearched(true)
		} catch (e) {
			console.log('검색 결과가 없습니다.', e)
		}
	}

	const fetchNonOptionKeywordSearch = async (keyword: string) => {
		const response = await axios.get(
			`${SERVER_API_URL}/foods/search?keyword=${keyword}`,
			{
				withCredentials: true,
			},
		)

		let fetchedFoodList = response.data.slice(0, 10)
		setFoodList(fetchedFoodList)
		setIsSearched(true)
	}

	const makeOptionKeywordUrl = async (clicked?: string) => {
		let url = `${SERVER_API_URL}/foods/search?keyword=${
			clicked ? clicked : keyword
		}&`

		await Object.keys(searchOptions).forEach((key: string) => {
			const option = searchOptions[key as SearchTitleType]
			if (option.view === 1) {
				url += `${key}=${option.gram}&`
				url += `${key}_con=${option.condition}&`
			}
		})
		return url.slice(0, -1)
	}

	return (
		<div className='absolute flex-row bg-white align-center w-500 h-[100vh] overflow-scroll'>
			<NavigatorExceptSearch />
			<KeywordSearchBar
				fetchOptionKeywordSearch={fetchOptionKeywordSearch}
				fetchKeywordSearch={fetchKeywordSearch}
				keyword={keyword}
				handleChangeKeyword={handleChangeKeyword}
				clearKeyword={clearKeyword}
				handleKeyUp={handleKeyUp}
			/>
			{relatedFoodList.length > 0 ? (
				<KeywordComponent
					relatedFoodList={relatedFoodList}
					keyword={keyword}
					fetchOptionKeywordSearch={fetchOptionKeywordSearch}
					focusedFoodIdx={focusedFoodIdx}
				/>
			) : (
				<></>
			)}
			<MallRanking
				fetchNonOptionKeywordSearch={fetchNonOptionKeywordSearch}
			/>
			<ViewsRanking fetchKeywordSearch={fetchKeywordSearch} />
			{/* <FooterMain/> */}
		</div>
	)
}

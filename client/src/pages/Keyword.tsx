import React, { useEffect, useState } from 'react'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios'
import KeywordComponent from '../components/KeywordComponent'
import NavigatorExceptSearch from '../components/NavigatorExceptSearch'
import SearchOptionBar from '../components/SearchOptionBar'
import { LuArrowDownWideNarrow, LuArrowUpNarrowWide } from 'react-icons/lu'
import FoodList from '../components/FoodList'
import InfoModal from '../components/modal/InfoModal'
import ComparisonModal from '../components/modal/ComparisonModal'
import ComparisonViewModal from '../components/modal/ComparisonViewModal'
import MallRanking from '../components/MallRanking'
import ViewsRanking from '../components/ViewsRanking'
import { useNavigate } from 'react-router-dom'
import FooterMain from '../components/FooterMain'

export type SearchTitleType = 'enerc' | 'chocdf' | 'prot' | 'fatce'
export type SearchOptionObjectType = {
	title: string
	gram: number
	condition: number
	view: number
}

export type SearchOptionType = {
	[key in SearchTitleType]: SearchOptionObjectType
}

export type FoodType = {
	foodId: number
	foodName: string
	manufacture: string
}

export type FoodListType = Array<FoodType> | []

const searchOptionList: SearchOptionType = {
	enerc: {
		title: '칼로리',
		gram: 0,
		condition: 1,
		view: 0,
	},
	chocdf: {
		title: '탄수화물',
		gram: 0,
		condition: 1,
		view: 0,
	},
	prot: {
		title: '단백질',
		gram: 0,
		condition: 1,
		view: 0,
	},
	fatce: {
		title: '지방',
		gram: 0,
		condition: 1,
		view: 0,
	},
}

export default function Keyword() {
	const [relatedFoodList, setRelatedFoodList] = useState([])
	const [searchOptions, setSearchOptions] = useState(searchOptionList)
	const [keyword, setKeyword] = useState<string>('')
	const [optionView, setOptionView] = useState<Boolean>(false)
	const [foodList, setFoodList] = useState<FoodListType>([])
	const [selectedKeyword, setSelectedKeyword] = useState('')
	const [focusedFoodIdx, setFocusedFoodIdx] = useState<number>(-1)
	const [isSearched, setIsSearched] = useState(false)
	const [selectedFoodIdx, setSelectedFoodIdx] = useState<number>(-1)
	const [comparison, setComparison] = useState<FoodListType>([])
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
		console.log(`keyword: ${keyword}`)
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

	const handleViewToggle = (title: SearchTitleType) => {
		const isView = searchOptions[title].view === 0 ? 1 : 0
		let newSearchOptions

		// 뷰가 꺼지면 gram을 0으로 되돌림
		if (isView === 0) {
			newSearchOptions = {
				...searchOptions,
				[title]: { ...searchOptions[title], view: isView, gram: 0 },
			}
		} else {
			newSearchOptions = {
				...searchOptions,
				[title]: { ...searchOptions[title], view: isView },
			}
		}

		setSearchOptions(newSearchOptions)
	}

	const changeGram = (title: SearchTitleType, gram: number) => {
		setSearchOptions({
			...searchOptions,
			[title]: { ...searchOptions[title], gram },
		})
	}

	const handleChangeGram = (
		title: SearchTitleType,
		e: React.ChangeEvent<HTMLInputElement>,
	) => {
		if (e.target.value === '') {
			changeGram(title, 0)
			return
		}

		if (typeof Number(e.target.value) !== 'number') {
			alert('숫자만 입력 가능합니다.')
			return
		}

		if (e.target.value.length > 3) {
			alert('숫자는 세 자리까지만 입력 가능합니다.')
			return
		}
		changeGram(title, Number(e.target.value))
	}

	const handleCondition = (title: SearchTitleType) => {
		const newCondition = searchOptions[title].condition === 0 ? 1 : 0
		const newSearchOptions = {
			...searchOptions,
			[title]: { ...searchOptions[title], condition: newCondition },
		}
		setSearchOptions(newSearchOptions)
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

	const handleOptionViewClick = () => {
		setOptionView(!optionView)
	}

	const handleSelectedFood = (idx: number) => {
		setSelectedFoodIdx(idx)
	}

	const addComparison = (foodItem: FoodType) => {
		if (comparison.length > 1) {
			alert('비교는 2개까지만 가능합니다.')
			return
		}
		setComparison([...comparison, { ...foodItem }])
	}

	const clearComparison = () => {
		setComparison([])
	}

	const deleteSpecificComparison = (idx: number) => {
		setComparison(comparison.filter((item, index) => index !== idx))
	}

	const handleComparisonView = () => {
		setViewComparison(!viewComparison)
	}

	return (
		<div className='absolute flex-row h-full bg-white align-center w-500'>
			{selectedFoodIdx !== -1 ? (
				<InfoModal
					selectedFoodIdx={selectedFoodIdx}
					handleSelectedFood={handleSelectedFood}
				/>
			) : (
				<></>
			)}
			{viewComparison ? (
				<ComparisonViewModal
					comparison={comparison}
					handleComparisonView={handleComparisonView}
				/>
			) : (
				<></>
			)}
			{comparison.length > 0 ? (
				<ComparisonModal
					comparison={comparison}
					clearComparison={clearComparison}
					deleteSpecificComparison={deleteSpecificComparison}
					handleComparisonView={handleComparisonView}
				/>
			) : (
				<></>
			)}
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
			<MallRanking fetchKeywordSearch={fetchKeywordSearch} />
			<ViewsRanking fetchKeywordSearch={fetchKeywordSearch} />
			{/* <FooterMain/> */}
		</div>
	)
}

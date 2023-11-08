import React, { useEffect, useRef, useState } from 'react'
import KeywordSearchPageBar from '../components/KeywordSearchPageBar'
import axios from 'axios'
import InfoModal from '../components/modal/InfoModal'
import ComparisonViewModal from '../components/modal/ComparisonViewModal'
import ComparisonModal from '../components/modal/ComparisonModal'
import KeywordComponent from '../components/KeywordComponent'
import FoodList from '../components/FoodList'

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
	imageUrl: string
	like: boolean
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

export default function Search() {
	const [relatedFoodList, setRelatedFoodList] = useState([])
	const [searchOptions, setSearchOptions] = useState(searchOptionList)
	const [keyword, setKeyword] = useState<string>('')
	const [optionView, setOptionView] = useState<Boolean>(false)
	const [foodList, setFoodList] = useState<FoodListType>([])
	const [selectedKeyword, setSelectedKeyword] = useState('')
	const [focusedFoodIdx, setFocusedFoodIdx] = useState<number>(-1)
	const [isSearched, setIsSearched] = useState(false)
	const [selectedFoodId, setSelectedFoodId] = useState<number>(-1)
	const [comparisonList, setComparisonList] = useState<FoodListType>([])
	const [viewComparison, setViewComparison] = useState(false)
	const [searchOnOff, setSearchOnOff] = useState(false)

	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const isInitialMount = useRef(true)

	// 최초 렌더링 시 url에 있는 code값 전달
	useEffect(() => {
		if (isInitialMount.current) {
			const url = new URL(window.location.href)

			// authorization server로부터 클라이언트로 리디렉션된 경우, authorization code가 함께 전달
			const keyword = url.searchParams.get('keyword')
			if (keyword) {
				fetchNonOptionKeywordSearch(keyword)
			}
			isInitialMount.current = false
		}
	})

	// 키워드가 초기화 될 경우 관련 검색어 초기화
	useEffect(() => {
		if (keyword === '') {
			setRelatedFoodList([])
			setFocusedFoodIdx(-1)
		} else {
			fetchKeywordSearch(keyword)
		}
	}, [keyword])

	// 식품 리스트가 변경 시 관련 검색어, 키워드 초기화
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

	const fetchNonOptionKeywordSearch = async (keyword: string) => {
		await axios
			.get(`${SERVER_API_URL}/foods/search?keyword=${keyword}`, {
				withCredentials: true,
			})
			.then(response => {
				let fetchedFoodList = response.data.slice(0, 10)
				setSelectedKeyword(keyword)
				setFoodList(fetchedFoodList)
				setIsSearched(true)
			})
			.catch(err => {
				console.log(err)
			})
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
		if (e.key.trim() === 'Enter') {
			modifySearchOn()
			// if (relatedFoodList.length === 0) return
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

	const modifySearchOn = () => {
		setSearchOnOff(true)
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

			let fetchedFoodList = response.data
			modifySearchOn()
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
		setSelectedFoodId(idx)
	}

	const addComparison = (foodItem: FoodType) => {
		if (comparisonList.length > 4) {
			alert('비교는 최대 5개까지만 가능합니다.')
			return
		}
		setComparisonList(prevComparison => [
			...prevComparison,
			{ ...foodItem },
		])
	}

	const clearComparison = () => {
		setComparisonList([])
	}

	const deleteSpecificComparison = (idx: number) => {
		setComparisonList(comparisonList.filter((item, index) => index !== idx))
	}

	const handleComparisonView = () => {
		setViewComparison(!viewComparison)
	}

	return (
		<div className='absolute flex-row h-full overflow-scroll bg-white w-500'>
			<KeywordSearchPageBar
				fetchKeywordSearch={fetchKeywordSearch}
				keyword={keyword}
				handleChangeKeyword={handleChangeKeyword}
				handleKeyUp={handleKeyUp}
				clearKeyword={clearKeyword}
				fetchOptionKeywordSearch={fetchOptionKeywordSearch}
			/>
			{selectedFoodId !== -1 && (
				<InfoModal
					selectedFoodId={selectedFoodId}
					handleSelectedFood={handleSelectedFood}
				/>
			)}
			{viewComparison && (
				<ComparisonViewModal
					comparisonList={comparisonList}
					handleComparisonView={handleComparisonView}
				/>
			)}
			{comparisonList.length > 0 && (
				<ComparisonModal
					comparisonList={comparisonList}
					clearComparison={clearComparison}
					deleteSpecificComparison={deleteSpecificComparison}
					handleComparisonView={handleComparisonView}
				/>
			)}
			{relatedFoodList.length > 0 && (
				<KeywordComponent
					relatedFoodList={relatedFoodList}
					keyword={keyword}
					fetchOptionKeywordSearch={fetchOptionKeywordSearch}
					focusedFoodIdx={focusedFoodIdx}
				/>
			)}
			<FoodList
				foodList={foodList}
				selectedKeyword={selectedKeyword}
				isSearched={isSearched}
				searchOnOff={searchOnOff}
				handleSelectedFood={handleSelectedFood}
				addComparison={addComparison}
			/>
		</div>
	)
}

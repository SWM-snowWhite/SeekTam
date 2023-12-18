import React, { useCallback, useEffect, useRef, useState } from 'react'
import KeywordSearchPageBar from '../components/KeywordSearchPageBar'
import axios from 'axios'
import InfoModal from '../components/modal/InfoModal'
import ComparisonViewModal from '../components/modal/ComparisonViewModal'
import ComparisonModal from '../components/modal/ComparisonModal'
import KeywordComponent from '../components/KeywordComponent'
import FoodList from '../components/FoodList'
import SearchOptionBar from '../components/SearchOptionBar'
import { useDispatch, useSelector } from 'react-redux'
import {
	updateSearchFirst,
	updateSearchOnOff,
	SearchCondition,
} from '../store/SearchInfoSlice'
import { RootState } from '..'
import { useNavigate } from 'react-router-dom'

export type SearchTitleTypeEng = 'calorie' | 'carbohydrate' | 'protein' | 'fat'

export type SearchTitleTypeKor = '열량' | '탄수화물' | '단백질' | '지방'
export type SearchOptionObjectType = {
	title: string
	gram: number
	condition: number
	view: number
}

const titleKrToEn = {
	열량: 'calorie',
	탄수화물: 'carbohydrate',
	단백질: 'protein',
	지방: 'fat',
}

export type SearchOptionType = {
	[key in SearchTitleTypeEng]: SearchOptionObjectType
}

export type FoodType = {
	foodId: number
	foodName: string
	companyName: string
	imageUrl: string
	like: boolean
}

export type FoodListType = Array<FoodType> | []

export type searchParams = {
	keyword: string
	carbohydrate: string
	carbohydrateCon: string
	calorie: string
	calorieCon: string
	protein: string
	proteinCon: string
	fat: string
	fatCon: string
}
const searchOptionList: SearchOptionType = {
	calorie: {
		title: '칼로리',
		gram: 0,
		condition: 1,
		view: 0,
	},
	carbohydrate: {
		title: '탄수화물',
		gram: 0,
		condition: 1,
		view: 0,
	},
	protein: {
		title: '단백질',
		gram: 0,
		condition: 1,
		view: 0,
	},
	fat: {
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
	const [foodList, setFoodList] = useState<FoodListType>([])
	const [selectedKeyword, setSelectedKeyword] = useState('')
	const [focusedFoodIdx, setFocusedFoodIdx] = useState<number>(-1)
	const [selectedFoodId, setSelectedFoodId] = useState<number>(-1)
	const { searchConditions } = useSelector(
		(state: RootState) => state.searchInfo,
	)
	const comparisonFood = useSelector(
		(state: RootState) => state.comparisonFood,
	)

	// 무한스크롤 관련 상태값
	const [end, setEnd] = useState(false) // 추가로 받아올 데이터 없을 시 더 이상 무한 스크롤 작동안하게 하는 상태값
	const [page, setPage] = useState(2) // 현재 페이지

	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const isInitialMount = useRef(true)
	const dispatcher = useDispatch()
	const navigator = useNavigate()

	//** useEffect 시작
	useEffect(() => {
		if (typeof window !== 'undefined') {
			const url = new URL(window.location.href)

			// authorization server로부터 클라이언트로 리디렉션된 경우, authorization code가 함께 전달
			const keyword = url.searchParams.get('keyword')!!
			const carbohydrate = url.searchParams.get('carbohydrate')!!
			const carbohydrateCon = url.searchParams.get('carbohydrateCon')!!
			const calorie = url.searchParams.get('calorie')!!
			const calorieCon = url.searchParams.get('calorieCon')!!
			const protein = url.searchParams.get('protein')!!
			const proteinCon = url.searchParams.get('proteinCon')!!
			const fat = url.searchParams.get('fat')!!
			const fatCon = url.searchParams.get('fatCon')!!
			const params = {
				keyword,
				carbohydrate,
				carbohydrateCon,
				fat,
				fatCon,
				protein,
				proteinCon,
				calorie,
				calorieCon,
			}

			if (keyword) {
				;(async () => {
					await fetchNonOptionKeywordSearch(params)
				})()
			}
			isInitialMount.current = false
		}
	}, [selectedKeyword])

	const initializeFoodList = () => {
		setRelatedFoodList([])
		setFocusedFoodIdx(-1)
	}
	// 키워드가 초기화 될 경우 관련 검색어 초기화
	useEffect(() => {
		if (keyword === '') {
			initializeFoodList()
		} else {
			fetchKeywordSearch(keyword)
		}
	}, [keyword])

	// 식품 리스트가 변경 시 관련 검색어, 키워드 초기화
	useEffect(() => {
		setRelatedFoodList([])
		setKeyword('')
	}, [foodList])

	const handleScroll = useCallback((): void => {
		const { innerHeight } = window
		const { scrollHeight, scrollTop } = document.documentElement

		if (Math.round(scrollTop + innerHeight) >= scrollHeight) {
			//Todo : 무한 스크롤
			fetchOptionKeywordSearch()
			setPage(page + 1)
		}
	}, [page])

	useEffect(() => {
		if (!end) {
			window.addEventListener('scroll', handleScroll, true)
		}
		return () => {
			window.removeEventListener('scroll', handleScroll, true)
		}
	}, [handleScroll])

	useEffect(() => {
		dispatcher(updateSearchFirst(true))
	}, [])

	//** useEffect 종료

	const handleChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
		setKeyword(e.target.value)
	}

	const clearKeyword = () => {
		setKeyword('')
	}

	const fetchNonOptionKeywordSearch = async (params: searchParams) => {
		console.log(`params : ${JSON.stringify(params)}`)
		const {
			keyword,
			carbohydrate,
			carbohydrateCon,
			protein,
			proteinCon,
			calorie,
			calorieCon,
			fat,
			fatCon,
		} = params

		axios
			.get(
				`${SERVER_API_URL}/foods/search?keyword=${keyword}&carbohydrate=${carbohydrate}&carbohydrateCon=${carbohydrateCon}&protein=${protein}&proteinCon=${proteinCon}&calorie=${calorie}&calorieCon=${calorieCon}&fat=${fat}&fatCon=${fatCon}`,
				{
					withCredentials: true,
				},
			)
			.then(response => {
				let fetchedFoodList = response.data.slice(0, 10)
				initializeFoodList()
				setSelectedKeyword(keyword)
				setFoodList(fetchedFoodList)
				dispatcher(updateSearchFirst(true))
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

	const modifySearchOn = () => {
		dispatcher(updateSearchOnOff(true))
	}

	const fetchOptionKeywordSearch = async (clicked?: string) => {
		if (!clicked && keyword === '') return

		const optionKeywordUrl = await makeOptionKeywordUrl(clicked)
		const foodName = clicked ? clicked : keyword

		setSelectedKeyword(foodName)
		// modifySearchOn()
		// dispatcher(updateSearchFirst(true))
		// initializeFoodList()
		navigator(optionKeywordUrl)
		try {
			// const response = await axios.get(optionKeywordUrl, {
			// 	withCredentials: true,
			// })
			// let fetchedFoodList = response.data
			// setFoodList(fetchedFoodList)
		} catch (e) {
			console.log('검색 결과가 없습니다.', e)
		}
	}

	const makeOptionKeywordUrl = async (clicked?: string) => {
		// let url = `${SERVER_API_URL}/foods/search?keyword=${
		// 	clicked ? clicked : keyword
		// }&`

		let url = `/search?keyword=${clicked ? clicked : keyword}&`

		await searchConditions.forEach((condition: SearchCondition) => {
			const { krName, content, contentUpDown } = condition
			const enName = titleKrToEn[krName]

			url += `${enName}=${content}&`
			url += `${enName}Con=${contentUpDown}&`
		})
		return url.slice(0, -1)
	}

	const handleSelectedFood = (idx: number) => {
		setSelectedFoodId(idx)
	}

	const handleComparisonView = () => {
		navigator('/comparison')
	}

	return (
		<div className='absolute flex-row h-[100vh] overflow-scroll bg-white w-500'>
			{viewComparison && (
				<ComparisonViewModal
					comparisonFood={comparisonFood}
					handleComparisonView={handleComparisonView}
				/>
			)}
			<KeywordSearchPageBar
				fetchKeywordSearch={fetchKeywordSearch}
				keyword={keyword}
				handleChangeKeyword={handleChangeKeyword}
				handleKeyUp={handleKeyUp}
				clearKeyword={clearKeyword}
				fetchOptionKeywordSearch={fetchOptionKeywordSearch}
			/>
			{relatedFoodList.length > 0 && (
				<KeywordComponent
					relatedFoodList={relatedFoodList}
					keyword={keyword}
					fetchOptionKeywordSearch={fetchOptionKeywordSearch}
					focusedFoodIdx={focusedFoodIdx}
				/>
			)}
			<SearchOptionBar searchOptionList={searchOptionList} />
			{selectedFoodId !== -1 && (
				<InfoModal
					selectedFoodId={selectedFoodId}
					handleSelectedFood={handleSelectedFood}
				/>
			)}
			{comparisonFood.length > 0 && (
				<ComparisonModal handleComparisonView={handleComparisonView} />
			)}

			<FoodList
				foodList={foodList}
				selectedKeyword={selectedKeyword}
				handleSelectedFood={handleSelectedFood}
			/>
		</div>
	)
}

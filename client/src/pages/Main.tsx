import React, { useEffect, useState } from 'react'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios'
import KeywordComponent from '../components/KeywordComponent'
import NavigatorExceptSearch from '../components/NavigatorExceptSearch'
import MallRanking from '../components/MallRanking'
import ViewsRanking from '../components/ViewsRanking'

import { FoodListType, SearchTitleTypeKor } from './Search'
import { useSelector } from 'react-redux'
import { RootState } from '..'
import { SearchCondition } from '../store/SearchInfoSlice'
import FooterMain from '../components/FooterMain'

export default function Main() {
	const [relatedFoodList, setRelatedFoodList] = useState([])
	const [foodList, setFoodList] = useState<FoodListType>([])
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

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

	const fetchNonOptionKeywordSearch = async (keyword: string) => {
		const response = await axios.get(
			`${SERVER_API_URL}/foods/search?keyword=${keyword}`,
			{
				withCredentials: true,
			},
		)

		let fetchedFoodList = response.data.slice(0, 10)
		setFoodList(fetchedFoodList)
	}

	return (
		<div className='absolute flex-row bg-white align-center w-500 h-[100vh] overflow-scroll'>
			<NavigatorExceptSearch />
			<KeywordSearchBar />
			<MallRanking
				fetchNonOptionKeywordSearch={fetchNonOptionKeywordSearch}
			/>
			<ViewsRanking fetchKeywordSearch={fetchKeywordSearch} />
			<FooterMain />
		</div>
	)
}

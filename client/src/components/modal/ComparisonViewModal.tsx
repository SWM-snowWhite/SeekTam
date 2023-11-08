import React, { useEffect, useState } from 'react'
import { FoodListType } from '../../pages/Main'
import axios from 'axios'
import { FoodInfoType } from '../../pages/FoodDetail'

export default function ComparisonViewModal({
	comparisonList,
	handleComparisonView,
}: {
	comparisonList: FoodListType
	handleComparisonView: () => void
}) {
	const [comparisonInfo, setComparisonInfo] = useState<FoodInfoType[]>([])
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		fetchComparisonInfo()
	}, [])

	const fetchComparisonInfo = async () => {
		try {
			const newComparisonInfo = await Promise.all(
				comparisonList.map(async item => {
					const response = await axios.get(
						`${SERVER_API_URL}/foods/search/detail?foodId=${item.foodId}`,
						{
							withCredentials: true,
						},
					)
					return response.data
				}),
			)
			setComparisonInfo(newComparisonInfo)
		} catch (error) {
			console.log('Error fetching comparisonList info:', error)
		}
	}
	return (
		<div className='card'>
			<div className='card-header'>
				<h3>주요 영양성분 하이라이팅</h3>
			</div>
			<div className='card-body'>
				<div className='image-placeholder'>
					<div>이미지1</div>
					<div>이미지2</div>
				</div>
				<div className='nutrition-facts'>
					<div className='fact-item'>
						<span>삼투압</span>
						<span>1123</span>
						<span>1123</span>
					</div>
					{/* 비슷한 구조의 fact-item div를 탄수화물 등의 항목에 대해 추가합니다. */}
					{/* ... */}
					<div className='fact-item'>
						<span>비타민A</span>
						<span>-</span>
						<span>-</span>
					</div>
					<div className='fact-item'>
						<span>비타민B</span>
						<span>-</span>
						<span>-</span>
					</div>
					<div className='fact-item'>
						<span>비타민C</span>
						<span>-</span>
						<span>-</span>
					</div>
				</div>
				<button className='btn-primary'>확인</button>
			</div>
		</div>
	)
}

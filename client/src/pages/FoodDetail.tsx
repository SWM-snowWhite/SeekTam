import axios from 'axios'
import React, { useEffect, useState } from 'react'
import FoodInfoComponent from '../components/FoodInfoComponent'

export type FoodInfoType = {
	foodId: number
	foodNm: number
	enerc: number
	prot: number
	fatce: number
	chocdf: number
	foodSize: number
	companyName: string
	sugar: number
}

export type View = 'ewg' | 'cancer' | 'allergy' | 'fodmap' | null

export default function FoodDetail() {
	const [foodInfo, setFoodInfo] = React.useState<FoodInfoType>({
		foodId: 0,
		foodNm: 0,
		enerc: 0,
		prot: 0,
		fatce: 0,
		chocdf: 0,
		foodSize: 0,
		companyName: '',
		sugar: 0,
	})
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const isInitialMount = React.useRef(true)

	const [selectedFoodId, setSelectedFoodId] = useState<number>(-1)
	useEffect(() => {
		if (isInitialMount.current) {
			const url = new URL(window.location.href)

			// authorization server로부터 클라이언트로 리디렉션된 경우, authorization code가 함께 전달
			const foodId = url.searchParams.get('foodId')

			if (foodId) {
				setSelectedFoodId(Number(foodId))
				fetchFoodDetailByFoodId()
			}
			isInitialMount.current = false
		}
	}, [])

	const fetchFoodDetailByFoodId = () => {
		axios
			.get(
				`${SERVER_API_URL}/foods/search/detail?foodId=${selectedFoodId}`,
				{
					withCredentials: true,
				},
			)
			.then(response => {
				setFoodInfo(response.data)
			})
			.catch(err =>
				console.log(`상품 상세 정보 받아오기가 실패하였습니다.`, err),
			)
	}
	return (
		<div className='absolute flex-row h-full overflow-scroll bg-white w-500'>
			<div>
				<div className='flex flex-col justify-around w-auto mb-10 h-50'>
					<div className='flex'>
						<div className='m-auto'>--</div>
						<h1 className='font-bold text-20 m-auto'>
							식품 상세정보
						</h1>
					</div>
					<hr className='text-grey200'></hr>
				</div>
			</div>
			<div className='flex-row items-center justify-center m-auto'>
				{foodInfo ? (
					<div>
						<p className='mt-10 text-center text-[#767676] text-12 font-bold'>
							{foodInfo.companyName}
						</p>
						<p className='w-auto m-5 font-semibold text-center text-15'>
							{foodInfo.foodNm}
						</p>
					</div>
				) : (
					<></>
				)}
			</div>
			{foodInfo ? <FoodInfoComponent foodInfo={foodInfo} /> : <></>}
		</div>
	)
}

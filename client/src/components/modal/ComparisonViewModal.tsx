import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { FoodListType, FoodType } from '../../pages/Search'
import { FoodInfoType } from '../../pages/FoodDetail'

export default function ComparisonViewModal({
	comparisonFood,
	handleComparisonView,
}: {
	comparisonFood: FoodListType
	handleComparisonView: () => void
}) {
	const [comparisonInfo, setComparisonInfo] = useState<FoodInfoType[]>([])
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'

	useEffect(() => {
		fetchComparisonInfo()
	}, [])

	const fetchComparisonInfo = async () => {
		try {
			const newComparisonInfo = await Promise.all(
				comparisonFood.map(async (item: FoodType) => {
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
			console.log('Error fetching ComparisonFood info:', error)
		}
	}

	const images = comparisonInfo.map(item => item.imageUrl)
	const foodNames = comparisonInfo.map(item => item.foodName)
	const energies = comparisonInfo.map(item => item.calorie)
	const proteins = comparisonInfo.map(item => item.protein)
	const fats = comparisonInfo.map(item => item.fat)
	const carbons = comparisonInfo.map(item => item.carbohydrate)
	const companyNames = comparisonInfo.map(item => item.companyName)
	const foodSizes = comparisonInfo.map(item => item.servingSize)
	// const sugars = comparisonInfo.map(item => item.sugar)

	return (
		<div className='h-full fixed bg-[black] w-500 bg-opacity-50 flex justify-center items-center'>
			<div className='border-main rounded-[10px] bg-[white] overflow-scroll'>
				<h1 className='flex justify-center mt-10 font-bold text-20'>
					상품비교
				</h1>
				<div className='p-10 flex-row m-auto bg-[white] w-350 text-14 text-main rounded-md'>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							상품이미지
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{images.map((value, idx) => (
								<img
									className='m-5 text-center text-info_s w-55'
									src={value ? value : DEFAULT_IMAGE}
									key={idx}
								>
									{value}
								</img>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							식품명
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{foodNames.map((value, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{value}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-auto font-bold rounded-md text-16'>
							회사명
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{companyNames.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-auto font-bold rounded-md text-16'>
							총 사이즈 (g)
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{foodSizes.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							열량 (kcal)
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{energies.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							탄수화물 (g)
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{carbons.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							단백질 (g)
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{proteins.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							지방
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{fats.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}
								</span>
							))}
						</div>
					</div>
					{/* <div className='m-10 mb-10'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							당류
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{sugars.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}g
								</span>
							))}
						</div>
					</div> */}
				</div>
				<button
					onClick={handleComparisonView}
					className='flex justify-center m-auto my-10 rounded-[10px] w-270 h-30 bg-main text-[white] text-20 font-bold'
				>
					확인
				</button>
			</div>
		</div>
	)
}

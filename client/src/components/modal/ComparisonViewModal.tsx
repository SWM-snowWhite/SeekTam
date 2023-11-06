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

	const foodNames = comparisonInfo.map(item => item.foodNm)
	const energies = comparisonInfo.map(item => item.enerc)
	const proteins = comparisonInfo.map(item => item.prot)
	const fats = comparisonInfo.map(item => item.fatce)
	const carbons = comparisonInfo.map(item => item.chocdf)
	const companyNames = comparisonInfo.map(item => item.companyName)
	const foodSizes = comparisonInfo.map(item => item.foodSize)
	const sugars = comparisonInfo.map(item => item.sugar)

	return (
		<div className='h-full fixed bg-[black] w-390 bg-opacity-50 flex justify-center items-center overflow-scroll'>
			<div className='border-main rounded-[10px] bg-[white]'>
				<h1 className='flex justify-center mt-10 font-bold text-20'>
					상품비교
				</h1>
				<div className='p-10 flex-row m-auto bg-[white] w-350 text-14 text-main rounded-md'>
					<div className='m-10 mb-30'>
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
					<div className='m-10 mb-30'>
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
					<div className='m-10 mb-30'>
						<div className='flex justify-center w-full p-3 m-auto font-bold rounded-md text-16'>
							총 사이즈
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
					<div className='m-10 mb-30'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							칼로리
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{energies.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}kcal
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-30'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							탄수화물
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{carbons.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}g
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-30'>
						<div className='flex justify-center w-full p-3 m-5 font-bold rounded-md text-16'>
							단백질
						</div>
						<hr></hr>
						<div className='flex justify-center'>
							{proteins.map((name, idx) => (
								<span
									className='m-5 text-center text-info_s w-[50%]'
									key={idx}
								>
									{name}g
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-30'>
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
									{name}g
								</span>
							))}
						</div>
					</div>
					<div className='m-10 mb-30'>
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
					</div>
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

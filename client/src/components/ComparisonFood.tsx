import React, { useState } from 'react'
import { FoodType } from '../pages/Search'
import { useDispatch, useSelector } from 'react-redux'
import { updateComparisonFood } from '../store/ComparisonSlice'
import { RootState } from '..'
export default function ComparisonFood({ food }: { food: FoodType }) {
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'
	const dispatcher = useDispatch()
	const comparisonList = useSelector(
		(state: RootState) => state.comparisonFood,
	)

	const handleDeleteFood = () => {
		dispatcher(
			updateComparisonFood(
				comparisonList.filter(item => item.foodId !== food.foodId),
			),
		)
	}
	return (
		<div className='rounded-md shadow-md border-1 bg-white border-g100 flex-col h-180 m-10'>
			<img
				src={food.imageUrl ? food.imageUrl : DEFAULT_IMAGE}
				alt={`${food.foodName} 이미지`}
				className='rounded-md w-80 h-80 m-auto'
			/>
			<h1 className='text-center text-grey900 text-14 mt-10'>
				{food.foodName}asdasdasd
			</h1>
			<div className='flex justify-center m-10 items-center'>
				<div
					onClick={handleDeleteFood}
					className='m-0 w-30 bg-grey200 text-grey900 rounded-md text-12 text-center'
				>
					<span>삭제</span>
				</div>
			</div>
		</div>
	)
}

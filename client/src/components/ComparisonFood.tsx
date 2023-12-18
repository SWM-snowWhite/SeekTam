import React, { useState } from 'react'
import { FoodType } from '../pages/Search'
import { useDispatch, useSelector } from 'react-redux'
import { filteredComparisonFood } from '../store/ComparisonSlice'
import { RootState } from '..'
export default function ComparisonFood({ food }: { food: FoodType }) {
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'
	const dispatcher = useDispatch()
	const comparisonList = useSelector(
		(state: RootState) => state.comparisonFood,
	)

	const handleDeleteFood = () => {
		dispatcher(filteredComparisonFood(food))
	}
	return (
		<div className='flex-col items-center justify-center m-10 bg-white rounded-md shadow-md border-1 border-g100 h-160'>
			<img
				src={food.imageUrl ? food.imageUrl : DEFAULT_IMAGE}
				alt={`${food.foodName} 이미지`}
				className='m-auto rounded-md w-80 h-80'
			/>
<<<<<<< HEAD
			<h1 className='mt-10 text-center text-g900 text-12 h-30'>
=======
			<h1 className='text-center text-grey900 text-14 mt-10'>
>>>>>>> 6ae5cca (feat: 회원탈퇴 화면 기능 적용)
				{food.foodName}
			</h1>
			<div
				onClick={handleDeleteFood}
				className='flex items-center justify-center m-10 rounded-md bg-g200 text-g900 text-12'
			>
				<p>삭제</p>
			</div>
		</div>
	)
}

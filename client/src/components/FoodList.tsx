import React, { useState } from 'react'
import { FoodListType, FoodType } from '../pages/Main'
import { BiMessageSquareAdd } from 'react-icons/bi'
import Food from './Food'
import NotFound from './NotFound'
import { useSelector } from 'react-redux'
import searchInfoSlice from '../store/SearchInfoSlice'
import { RootState } from '..'

export default function FoodList({
	foodList,
	selectedKeyword,
	handleSelectedFood,
	addComparison,
}: {
	foodList: FoodListType
	selectedKeyword: string
	handleSelectedFood: (foodId: number) => void
	addComparison: (foodItem: FoodType) => void
}) {
	const { isSearchFirst, isOptionOpened, isSearchOn } = useSelector(
		(state: RootState) => state.searchInfo,
	)
	return (
		<div className='flex flex-col justify-between w-full m-auto f-full '>
			{selectedKeyword !== '' && (
				<div className='m-20 text-22'>
					<span className='font-bold'>'{selectedKeyword}'</span>
					<span className=''>에 대한 검색 결과</span>
				</div>
			)}
			{!isSearchFirst && isSearchOn ? (
				<div className='flex w-full'>
					<NotFound />
				</div>
			) : (
				<div className='grid items-start grid-cols-2 p-0 m-auto mt-0 align-top gap-y-40 gap-x-50'>
					{foodList.length > 0 &&
						foodList.map((foodItem: FoodType) => (
							<Food
								key={foodItem.foodId}
								foodItem={foodItem}
								addComparison={addComparison}
							/>
						))}
				</div>
			)}
		</div>
	)
}

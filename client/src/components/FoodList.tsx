import React, { useState } from 'react'
import { FoodListType, FoodType } from '../pages/Main'
import { BiMessageSquareAdd } from 'react-icons/bi'
import Food from './Food'
import NotFound from './NotFound'

export default function FoodList({
	foodList,
	selectedKeyword,
	isSearched,
	searchOnOff,
	handleSelectedFood,
	addComparison,
}: {
	foodList: FoodListType
	selectedKeyword: string
	isSearched: boolean
	searchOnOff: boolean
	handleSelectedFood: (foodId: number) => void
	addComparison: (foodItem: FoodType) => void
}) {
	return (
		<div className='flex flex-col justify-between w-full h-full m-auto'>
			{selectedKeyword !== '' && (
				<div className='m-20 text-22'>
					<span className='font-bold'>'{selectedKeyword}'</span>
					<span className=''>에 대한 검색 결과</span>
				</div>
			)}
			{!isSearched && searchOnOff ? (
				<div className='flex items-center justify-center w-full h-full'>
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

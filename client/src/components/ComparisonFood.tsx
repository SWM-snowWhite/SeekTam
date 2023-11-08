import React, { useState } from 'react'
import { FoodListType, FoodType } from '../pages/Search'
export default function ComparisonFood({
	comparisonItem,
	deleteSpecificComparison,
}: {
	comparisonItem: FoodType
	deleteSpecificComparison: (idx: number) => void
}) {
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'

	return (
		<div className=' rounded-md shadow-md border-1 border-grey100 flex-col h-140 m-10'>
			<img
				src={
					comparisonItem.imageUrl
						? comparisonItem.imageUrl
						: DEFAULT_IMAGE
				}
				alt={comparisonItem.foodName}
				className='object-cover rounded-md w-80 h-80 m-0'
			/>
			<h1 className='font-bold text-center m-auto text-grey900 text-14 mt-5'>
				{comparisonItem.foodName}
			</h1>
			<div className='flex justify-center m-10 items-center'>
				<div className='m-0 w-30 bg-grey200 text-grey900 rounded-md text-12 text-center'>
					<span>삭제</span>
				</div>
			</div>
		</div>
	)
}

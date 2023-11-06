import axios from 'axios'
import React, { useState } from 'react'
import { AiFillHeart, AiOutlineHeart, AiOutlinePlus } from 'react-icons/ai'
import { FoodListType, FoodType } from '../pages/Search'
import { GiCancel } from 'react-icons/gi'
export default function ComparisonFood({
	comparisonItem,
	deleteSpecificComparison
}: {
	comparisonItem: FoodType
	deleteSpecificComparison: (idx: number) => void
}) {
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'

	return (
		<div className='rounded-md shadow-md w-104 h-160 border-1 border-grey100'>
			<div className='flex flex-col items-center justify-center p-4 bg-white rounded-lg shadow-md'>
				<img
					src={comparisonItem.imageUrl ? comparisonItem.imageUrl : DEFAULT_IMAGE}
					alt={comparisonItem.foodName}
					className='object-cover rounded-md w-80 h-80'
				/>
				<h1 className='font-bold text-center text-grey900 text-16'>{comparisonItem.foodName}</h1>
				<div className='flex w-180'>
                <GiCancel onClick={() => deleteSpecificComparison(comparisonItem.foodId)} size={20} className='m-auto cursor-pointer text-end text-info_s'/>
				</div>
			</div>
		</div>
	)
}

import React from 'react'
import { FoodInfoType } from '../pages/FoodDetail'

export default function FoodInfoComponent({
	foodInfo,
}: {
	foodInfo: FoodInfoType
}) {
	return (
		<div className='flex-row m-auto mt-20 w-150 text-14 text-main'>
			<div className='flex justify-between'>
				<span className='p-3 m-5 rounded-md border-1 border-sub'>
					에너지(kcal)
				</span>
				<span className='p-3 m-5 text-info_s'>
					{foodInfo.calorie ? foodInfo.calorie : 0}
				</span>
			</div>
			<div className='flex justify-between'>
				<span className='p-3 m-5 rounded-md border-1 border-sub'>
					탄수화물
				</span>
				<span className='p-3 m-5 text-info_s'>
					{foodInfo.carbohydrate ? foodInfo.carbohydrate : 0}g
				</span>
			</div>
			<div className='flex justify-between'>
				<span className='p-3 m-5 rounded-md border-1 border-sub'>
					단백질
				</span>
				<span className='p-3 m-5 text-info_s'>
					{foodInfo.protein ? foodInfo.protein : 0}g
				</span>
			</div>
			<div className='flex justify-between'>
				<span className='p-3 m-5 rounded-md border-1 border-sub'>
					지방
				</span>
				<span className='p-3 m-5 text-info_s'>
					{foodInfo.fat ? foodInfo.fat : 0}g
				</span>
			</div>
			<div className='flex justify-between'>
				<span className='p-3 m-5 rounded-md border-1 border-sub'>
					당류
				</span>
				<span className='p-3 m-5 text-info_s'>
					{foodInfo.totalSugar ? foodInfo.totalSugar : 0}g
				</span>
			</div>
		</div>
	)
}

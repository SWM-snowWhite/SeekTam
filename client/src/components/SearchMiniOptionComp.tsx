import React from 'react'
import { SearchCondition } from '../store/SearchInfoSlice'

export default function SearchMiniOptionComp({
	searchCondition,
}: {
	searchCondition: SearchCondition
}) {
	return (
		<div className='flex items-center justify-center h-40 mx-5 w-80 bg-p800 rounded-xl'>
			<span className='text-white'>{searchCondition.name}</span>
		</div>
	)
}

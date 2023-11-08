import React from 'react'

export default function Navigator({ title }: { title: string }) {
	const onBackClick = () => {}
	const onSearchClick = () => {}

	return (
		<div className='flex items-center justify-between p-4 text-white bg-main-500'>
			<button onClick={onBackClick} className='text-lg cursor-pointer'>
				뒤로가기
			</button>
			<h1 className='text-xl'>{title}</h1>
		</div>
	)
}

import React from 'react'

function Navigation() {
	const onBackClick = () => {}
	const currentPage = '검색'
	const onSearchClick = () => {}

	return (
		<div className='flex items-center justify-between p-4 text-white bg-main-500'>
			<button onClick={onBackClick} className='text-lg cursor-pointer'>
				뒤로가기
			</button>
			<h1 className='text-xl'>{currentPage}</h1>
			<button onClick={onSearchClick} className='text-lg'>
				검색
			</button>
		</div>
	)
}

export default Navigation

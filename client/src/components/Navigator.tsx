import React from 'react'

function Navigation() {
	const onBackClick = () => {}
	const currentPage = '검색'
	const onSearchClick = () => {}

	return (
		<div className='bg-blue-500 p-4 text-white flex justify-between items-center'>
			<button onClick={onBackClick} className='text-lg'>
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

import React, { useEffect, useState } from 'react'
import { PiMagnifyingGlass } from 'react-icons/pi'
import { GiCancel } from 'react-icons/gi'
import { useNavigate } from 'react-router-dom'

export default function KeywordSearchBar() {
	const navigator = useNavigate()
	const openSearchPage = (): void => {
		navigator('/search')
	}
	return (
		<div
			className='relative flex m-auto bg-white border-2 rounded-full cursor-pointer mt-90 border-main w-450 h-50'
			onClick={openSearchPage}
		>
			<input
				className='text-main w-[90%] focus:outline-none rounded-2xl ml-20 placeholder:text-16 placeholder:text-g400 '
				placeholder='찾고자 하는 식품명을 입력해 주세요'
			/>
			<button>
				<PiMagnifyingGlass size={26} className='mr-10 text-main' />
			</button>
		</div>
	)
}

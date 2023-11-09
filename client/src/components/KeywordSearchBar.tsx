import React, { useEffect, useState } from 'react'
import { PiMagnifyingGlass } from 'react-icons/pi'
import { GiCancel } from 'react-icons/gi'
import { useNavigate } from 'react-router-dom'

export default function KeywordSearchBar({
	fetchKeywordSearch,
	keyword,
	handleChangeKeyword,
	clearKeyword,
	handleKeyUp,
	fetchOptionKeywordSearch,
}: {
	fetchKeywordSearch: (keyword: string) => void
	keyword: string
	handleChangeKeyword: (event: React.ChangeEvent<HTMLInputElement>) => void
	clearKeyword: () => void
	handleKeyUp: (event: React.KeyboardEvent<HTMLInputElement>) => void
	fetchOptionKeywordSearch: () => void
}) {
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
				onChange={handleChangeKeyword}
				onKeyUp={handleKeyUp}
				placeholder='찾고자 하는 식품명을 입력해 주세요'
				value={keyword}
				spellCheck={false}
			/>
			{keyword ? (
				<button onClick={clearKeyword}>
					<GiCancel
						size={20}
						className='mr-5 text-[white] bg-main rounded-2xl'
					/>
				</button>
			) : (
				<></>
			)}
			<button onClick={() => fetchOptionKeywordSearch()}>
				<PiMagnifyingGlass size={26} className='mr-10 text-main' />
			</button>
		</div>
	)
}

import React, { useEffect, useState } from 'react'
import { PiMagnifyingGlass } from 'react-icons/pi'
import { GiCancel } from 'react-icons/gi'
import { useNavigate } from 'react-router-dom'
import { AiOutlineArrowLeft } from 'react-icons/ai'

export default function KeywordSearchPageBar({
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
	const navigate = useNavigate()
	
	return (
		<div 
			className='flex flex-col m-auto mt-40 rounded-md border-main w-450 h-50'
		>
			<div className='flex '>
				<AiOutlineArrowLeft 
					className='flex m-auto mr-10'
					size={30} 
					onClick={() => navigate(-1)}/>
				<input
					className='text-16 text-main w-[100%] rounded-2xl focus:outline-none ml-10 placeholder:text-16 placeholder:text-grey400 '
					onChange={handleChangeKeyword}
					onKeyUp={handleKeyUp}
					placeholder='찾고자 하는 식품명을 입력해 주세요'
					value={keyword}
				/>
				{
					keyword ? (
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
			<hr className='mt-10 text-grey200'></hr>
		</div>
	)
}

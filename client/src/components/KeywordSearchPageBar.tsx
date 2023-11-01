import React, { useEffect, useState } from 'react'
import { PiMagnifyingGlass } from 'react-icons/pi'
import { GiCancel } from 'react-icons/gi'
import { useNavigate } from 'react-router-dom'

export default function KeywordSearchPageBar() {

	const fetchOptionKeywordSearch = () => {
		
	}
	
	return (
		<div 
			className='flex m-auto mt-40 border-2 rounded-md border-main w-450 h-50'
		>
			<button>
				--
			</button>	
			<input
				className='text-main w-[90%] rounded-2xl focus:outline-none ml-10 placeholder:text-16 placeholder:text-grey400 '
				// onChange={handleChangeKeyword}
				// onKeyUp={handleKeyUp}
				placeholder='찾고자 하는 식품명을 입력해 주세요'
				// value={keyword}
			/>
			{/* {
				keyword ? (
					<button onClick={clearKeyword}>
						<GiCancel
							size={20}
							className='mr-5 text-[white] bg-main rounded-2xl'
						/>
					</button>
				) : (
					<></>
				)} */}
			<button onClick={() => fetchOptionKeywordSearch()}>
				<PiMagnifyingGlass size={26} className='mr-10 text-main' />
			</button>
		<hr></hr>
		</div>
	)
}

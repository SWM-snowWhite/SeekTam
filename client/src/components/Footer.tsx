import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { BsFillPersonLinesFill } from 'react-icons/bs'
import { AiFillHome, AiFillHeart } from 'react-icons/ai'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'

type currentPageProps = "home" | "profile" | "wishlist"

function Footer() {
	const navigator = useNavigate()
	const currentPage = useSelector((state: RootState) => state.currentPage)
	
	const onProfileClick = () => {
		navigator('/profile')
	}

	const onHomeClick = () => {
		navigator('/main')
	}

	const onWishlistClick = () => {
		navigator('/wishlist')
	}

	return (
		<div className='absolute bottom-0 z-0 flex justify-around p-4 m-auto bg-white rounded-lg shadow-main border-grey200 w-500 items-around text-main border-1'>
			<div className='flex-row items-center justify-center m-0 cursor-pointer'>
				<BsFillPersonLinesFill size={30} onClick={onProfileClick} className={currentPage === "profile" ? "text-main" : "text-grey300"}/>
				<label className={currentPage === "profile" ? "text-main" : "text-grey300"}>내 정보</label>
			</div>
			<div className='cursor-pointer'>
				<AiFillHome size={30} onClick={onHomeClick} className={currentPage === "home" ? "text-main" : "text-grey300"}/>
				<label className={currentPage === "home" ? "text-main" : "text-grey300"}>홈 화면</label>
			</div>
			<div className='cursor-pointer'>
				<AiFillHeart size={30} onClick={onWishlistClick} className={currentPage === "wishlist" ? "text-main" : "text-grey300"}/>
				<label className={currentPage === "wishlist" ? "text-main" : "text-grey300"}>찜 목록</label>
			</div>
		</div>
	)
}

export default Footer

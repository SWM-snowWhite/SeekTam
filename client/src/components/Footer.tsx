import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { BsFillPersonLinesFill } from 'react-icons/bs'
import { AiFillHome, AiFillHeart } from 'react-icons/ai'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import { currentPageUpdate } from '../store/CurrentPageSlice'

type currentPageProps = 'home' | 'profile' | 'wishlist'

function Footer() {
	const navigator = useNavigate()
	const dispatcher = useDispatch()
	const currentPage = useSelector((state: RootState) => state.currentPage)

	useEffect(() => {
		console.log(`현재 페이지 : ${currentPage}`)
	})
	const handleProfileClick = () => {
		dispatcher(currentPageUpdate('profile'))
		navigator('/profile')
	}

	const handleHomeClick = () => {
		dispatcher(currentPageUpdate('home'))
		navigator('/main')
	}

	const handleWishlistClick = () => {
		dispatcher(currentPageUpdate('wishlist'))
		navigator('/wishlist')
	}

	return currentPage !== '' ? (
		<div className='absolute bottom-0 flex justify-around p-4 m-auto bg-white rounded-lg shadow-main border-g200 w-500 items-around text-main border-1'>
			<div
				onClick={handleProfileClick}
				className='flex-row items-center justify-center m-0 cursor-pointer'
			>
				<BsFillPersonLinesFill
					size={30}
					className={
						currentPage === 'profile' ? 'text-main' : 'text-g300'
					}
				/>
				<span
					className={
						currentPage === 'profile' ? 'text-main' : 'text-g300'
					}
				>
					내 정보
				</span>
			</div>
			<div onClick={handleHomeClick} className='cursor-pointer'>
				<AiFillHome
					size={30}
					className={
						currentPage === 'home' ? 'text-main' : 'text-g300'
					}
				/>
				<span
					className={
						currentPage === 'home' ? 'text-main' : 'text-g300'
					}
				>
					홈 화면
				</span>
			</div>
			<div onClick={handleWishlistClick} className='cursor-pointer'>
				<AiFillHeart
					size={30}
					className={
						currentPage === 'wishlist' ? 'text-main' : 'text-g300'
					}
				/>
				<span
					className={
						currentPage === 'wishlist' ? 'text-main' : 'text-g300'
					}
				>
					찜 목록
				</span>
			</div>
		</div>
	) : (
		<div></div>
	)
}

export default Footer

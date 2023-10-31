import React from 'react'
import { useNavigate } from 'react-router-dom'
import { BsFillPersonLinesFill } from 'react-icons/bs'
import { AiFillHome, AiFillHeart } from 'react-icons/ai'
function Footer() {
	const navigate = useNavigate()

	const onProfileClick = () => {
		navigate('/profile')
	}

	const onHomeClick = () => {
		navigate('/')
	}

	const onWishlistClick = () => {
		navigate('/wishlist')
	}

	return (
		<div className='absolute bottom-0 flex justify-around p-4 m-auto border-white rounded-lg w-500 items-around text-main border-1'>
			<div className='flex-row items-center justify-center m-0'>
				<BsFillPersonLinesFill size={30} onClick={onProfileClick} className="text-white"/>
				<label className='text-lg text-white'>내 정보</label>
			</div>
			<div>
				<AiFillHome size={30} onClick={onHomeClick} className="text-white"/>
				<label className='text-lg text-white'>홈 화면</label>
			</div>
			<div>
				<AiFillHeart size={30} onClick={onWishlistClick} className="text-white"/>
				<label className='text-lg text-white'>찜 목록</label>
			</div>
		</div>
	)
}

export default Footer

import React from 'react'
import { useNavigate } from 'react-router-dom'

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
		<div className='bg-blue-500 p-4 text-white flex justify-between'>
			<button onClick={onProfileClick} className='text-lg'>
				내 정보
			</button>
			<button onClick={onHomeClick} className='text-lg'>
				홈 화면
			</button>
			<button onClick={onWishlistClick} className='text-lg'>
				찜 목록
			</button>
		</div>
	)
}

export default Footer

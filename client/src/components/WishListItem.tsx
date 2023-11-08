import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { AiOutlineHeart } from 'react-icons/ai'
import { AiFillHeart } from 'react-icons/ai'

export default function WishListItem({
	foodId,
	foodName,
	imageUrl,
	liked,
}: {
	foodId: number
	foodName: string
	imageUrl: string
	liked: boolean
}) {
	const [stateLike, setStateLike] = useState(liked)
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'

	const handleUnLikeFood = () => {
		axios
			.delete(`${REACT_APP_SERVER_API_URL}/member/unlike`, {
				data: {
					foodId: foodId,
				},
				withCredentials: true,
			})
			.then(_ => {
				setStateLike(prevStateLike => !prevStateLike)
			})
			.catch(_ => {
				console.log('fail')
			})
	}

	const handleLikeFood = () => {
		axios
			.put(
				`${REACT_APP_SERVER_API_URL}/member/like`,
				{ foodId },
				{ withCredentials: true },
			)
			.then(_ => {
				setStateLike(prevStateLike => !prevStateLike)
			})
			.catch(_ => {
				console.log('fail')
			})

		setStateLike(!stateLike)
	}

	return (
		<div className='p-4 mb-20 w-150 h-210'>
			<div className='p-4 bg-white rounded-lg shadow-md'>
				<img
					src={imageUrl ? imageUrl : DEFAULT_IMAGE}
					alt={foodName}
					className='object-cover rounded-lg w-150 h-150'
				/>
				<div className='flex items-center justify-center font-medium text-center text-grey900 text-14 h-30'>
					{foodName}
				</div>
				<div className='flex w-[80%] justify-center items-center bg-grey100 rounded-lg m-auto my-5'>
					{stateLike ? (
						<AiFillHeart
							onClick={handleUnLikeFood}
							className='text-red mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiFillHeart>
					) : (
						<AiOutlineHeart
							onClick={handleLikeFood}
							className='text-grey400 mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiOutlineHeart>
					)}
				</div>
			</div>
		</div>
	)
}

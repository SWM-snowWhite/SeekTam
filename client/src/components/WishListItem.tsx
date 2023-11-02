import axios from 'axios'
import React, { useState } from 'react'
import { AiOutlineHeart } from 'react-icons/ai'
import { AiFillHeart } from 'react-icons/ai'

export default function WishListItem({
	foodId,
	foodName,
	imageUrl,
	like,
}: {
	foodId: number
	foodName: string
	imageUrl: string
	like: boolean
}) {
	const [stateLike, setStateLike] = useState(like)
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	const handleUnLikeFood = () => {
		axios
			.delete(`${REACT_APP_SERVER_API_URL}/member/unlike`,{
				data: {
					"foodId": foodId 
				},
					withCredentials: true 
			})
			.then(_ => {
				console.log('success')
				setStateLike(!stateLike)
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
				console.log('success')
				setStateLike(!stateLike)
			})
			.catch(_ => {
				console.log('fail')
			})

		setStateLike(!stateLike)
	}

	return (
		<div className='p-4 mb-20 w-170 h-210'>
			<div className='p-4 bg-white rounded-lg shadow-md'>
				<img
					src={imageUrl}
					alt={foodName}
					className='object-cover rounded-lg w-170 h-160'
				/>
				<h1 className='font-bold text-center text-grey900'>진라면{foodName}</h1>
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

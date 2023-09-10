import axios from 'axios'
import React, { useState } from 'react'
import { AiOutlineHeart } from 'react-icons/ai'
import { AiFillHeart } from 'react-icons/ai'

export default function WishListItem({
	food_id,
	food_name,
	image_url,
	like,
}: {
	food_id: number
	food_name: string
	image_url: string
	like: boolean
}) {
	const [stateLike, setStateLike] = useState(like)
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	const handleLikeFood = () => {
		let likeOrUnLike = stateLike ? 'like' : 'unlike'

		axios
			.put(
				`${REACT_APP_SERVER_API_URL}/member/${likeOrUnLike}`,
				{ food_id },
				{ withCredentials: true },
			)
			.then(_ => {
				console.log('success')
			})
			.catch(_ => {
				console.log('fail')
			})

		setStateLike(!stateLike)
	}

	return (
		<div className='w-170 h-210 p-4 mb-20'>
			<div className='bg-white rounded-lg shadow-md p-4'>
				<img
					src={image_url}
					alt={food_name}
					className='w-170 h-160 object-cover rounded-lg'
				/>
				<div className='flex w-full'>
					{stateLike ? (
						<AiFillHeart
							onClick={handleLikeFood}
							className='text-main mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiFillHeart>
					) : (
						<AiOutlineHeart
							onClick={handleLikeFood}
							className='text-main mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiOutlineHeart>
					)}

					<span className='font-bold mw-10 mw-10 my-5'>
						{food_name}
					</span>
				</div>
			</div>
		</div>
	)
}

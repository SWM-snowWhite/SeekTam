import axios from 'axios'
import React, { useState } from 'react'
import { AiOutlineHeart } from 'react-icons/ai'
import { AiFillHeart } from 'react-icons/ai'
export default function WishListItem({
	id,
	title,
	image,
	like,
}: {
	id: number
	title: string
	image: string
	like: boolean
}) {
	const [stateLike, setStateLike] = useState(like)

	const handleLikeFood = () => {
		let likeOrUnLike = like ? 'like' : 'unlike'

		axios
			.post(`/member/${likeOrUnLike}`, { id }, { withCredentials: true })
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
					src={image}
					alt={title}
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

					<span className='font-bold mw-10 mw-10 my-5'>{title}</span>
				</div>
			</div>
		</div>
	)
}

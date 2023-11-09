import axios from 'axios'
import React, { useState } from 'react'
import { AiFillHeart, AiOutlineHeart, AiOutlinePlus } from 'react-icons/ai'
import { FoodType } from '../pages/Search'

export default function Food({
	foodItem,
	addComparison,
}: {
	foodItem: FoodType
	addComparison: (foodItem: FoodType) => void
}) {
	const [stateLike, setStateLike] = useState(foodItem.like)
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	const handleUnLikeFood = () => {
		axios
			.delete(`${SERVER_API_URL}/member/unlike`, {
				data: {
					foodId: foodItem.foodId,
				},
				withCredentials: true,
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
				`${SERVER_API_URL}/member/like`,
				{
					foodId: foodItem.foodId,
				},
				{
					withCredentials: true,
				},
			)
			.then(_ => {
				console.log('success')
				setStateLike(!stateLike)
			})
			.catch(_ => {
				console.log('fail')
			})
	}

	const handleFoodDetail = () => {
		axios
			.get(
				`${SERVER_API_URL}/foods/search/detail?foodId=${foodItem.foodId}`,
				{
					withCredentials: true,
				},
			)
			.then(_ => {
				console.log('success')
			})
			.catch(_ => {
				console.log('fail')
			})
	}
	return (
		<div className='rounded-md shadow-md w-190 h-270 border-1 border-g100'>
			<div className='flex flex-col items-center justify-center p-4 bg-white rounded-lg shadow-md'>
				<img
					src={foodItem.imageUrl ? foodItem.imageUrl : DEFAULT_IMAGE}
					alt={foodItem.foodName}
					className='object-cover rounded-lg w-190 h-190'
					onClick={handleFoodDetail}
				/>
				<h1
					onClick={handleFoodDetail}
					className='mt-5 font-bold text-g900 text-14'
				>
					{foodItem.foodName}
				</h1>
				<div className='flex w-180'>
					<div
						onClick={() => addComparison(foodItem)}
						className='flex items-center justify-center m-auto rounded-md cursor-pointer w-90 bg-g100 hover:bg-info '
					>
						<AiOutlinePlus size={22} className='mx-5 text-p800' />
						<span className='mx-5 my-5 text-14 text-p800'>
							비교함
						</span>
					</div>
					<div className='flex items-center justify-center m-auto my-5 rounded-lg bg-g100'>
						{stateLike ? (
							<AiFillHeart
								onClick={handleUnLikeFood}
								className='my-5 cursor-pointer w-50 text-red mw-10 justify-self-center'
								size={22}
							></AiFillHeart>
						) : (
							<AiOutlineHeart
								onClick={handleLikeFood}
								className='my-5 cursor-pointer w-50 text-p800 mw-10 justify-self-center'
								size={22}
							></AiOutlineHeart>
						)}
					</div>
				</div>
			</div>
		</div>
	)
}

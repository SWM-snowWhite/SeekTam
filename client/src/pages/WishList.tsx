import React, { useEffect, useState } from 'react'
import WishListItem from '../components/WishListItem'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import { currentPageUpdate } from '../store/CurrentPageSlice'

type WishListProps = {
	foodId: number
	foodName: string
	imageUrl: string
	like: boolean
}

export default function WishList() {
	const [wishlistItems, setWishListItems] = useState<WishListProps[]>()
	const dispatcher = useDispatch()
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	
	useEffect(() => {
		getWishListItems()
		try {
			dispatcher(currentPageUpdate('wishlist'))
		} catch(error) {
			console.log(error)
		}
	}, [])

	const getWishListItems = () => {
		axios
			.get(`${REACT_APP_SERVER_API_URL}/member/like-list`, {
				withCredentials: true,
			})
			.then(res => {
				const data: WishListProps[] = res.data.map(
					(item: WishListProps, idx: number) => {
						return {
							...item,
							like: true,
							imageUrl: `https://placekitten.com/${
								idx + 300
							}/200`,
						}
					},
				)

				setWishListItems(data)
			})
			.catch(err => {
				console.log("Error: Can't get wishlist items")
			})
	}
	return (
		<div className='absolute h-full p-4 bg-white w-500 m-0min-h-screen'>
			<div>
				<div className='flex flex-col justify-center w-auto mb-10 h-50'>
					<h1 className='m-10 font-bold text-20'>
						찜 목록
					</h1>
					<hr className='text-grey200'></hr>
				</div>
				<div className='grid max-w-screen-md grid-cols-1 gap-4 mx-auto md:grid-cols-3'>
				{wishlistItems ? (
					wishlistItems.map((item: WishListProps, idx: number) => (
						<WishListItem
							key={idx}
							foodId={item.foodId}
							foodName={item.foodName}
							imageUrl={item.imageUrl}
							like={item.like}
						/>
					))
				) : (
					<></>
				)}
				</div>
			</div>
		</div>
	)
}

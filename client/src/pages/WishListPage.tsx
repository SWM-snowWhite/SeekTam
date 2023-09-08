import React, { useEffect, useState } from 'react'
import WishListItem from '../components/WishListItem'
import axios from 'axios'

type WishListPageProps = {
	food_id: number
	food_name: string
	image_url: string
	like: boolean
}

export default function WishListPage() {
	const [wishlistItems, setWishListItems] = useState<WishListPageProps[]>()
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		getWishListItems()
	}, [])

	const getWishListItems = () => {
		axios
			.get(`${REACT_APP_SERVER_API_URL}/member/like-list`, {
				withCredentials: true,
			})
			.then(res => {
				const data: WishListPageProps[] = res.data.map(
					(item: WishListPageProps, idx: number) => {
						return {
							...item,
							like: true,
							image_url: `https://placekitten.com/${
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
		<div className='w-auto m-0min-h-screen p-4'>
			<div className='max-w-screen-md mx-auto grid grid-cols-1 md:grid-cols-3 gap-4'>
				{wishlistItems ? (
					wishlistItems.map((item: WishListPageProps) => (
						<WishListItem
							key={item.food_id}
							food_id={item.food_id}
							food_name={item.food_name}
							image_url={item.image_url}
							like={item.like}
						/>
					))
				) : (
					<></>
				)}
			</div>
		</div>
	)
}

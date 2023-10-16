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
		<div className='w-auto p-4 m-0min-h-screen'>
			<div className='grid max-w-screen-md grid-cols-1 gap-4 mx-auto md:grid-cols-3'>
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

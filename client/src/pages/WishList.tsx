import React, { useEffect, useState } from 'react'
import WishListItem from '../components/WishListItem'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import { currentPageUpdate } from '../store/CurrentPageSlice'
import Navigator from '../components/Navigator'

type WishListProps = {
	foodId: number
	foodName: string
	imageUrl: string
	liked: boolean
}

export default function WishList() {
	const [wishlistItems, setWishListItems] = useState<WishListProps[]>()
	const dispatcher = useDispatch()
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		getWishListItems()
		dispatcher(currentPageUpdate('wishlist'))
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
							liked: true,
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
		<div className='absolute bg-white w-500 m-0 h-[100vh]'>
			<Navigator title='찜 목록' />
			<div className='mt-70'>
				<div className='grid grid-cols-1 gap-20 mx-auto md:grid-cols-3'>
					{wishlistItems ? (
						wishlistItems.map(
							(item: WishListProps, idx: number) => (
								<WishListItem
									key={idx}
									foodId={item.foodId}
									foodName={item.foodName}
									imageUrl={item.imageUrl}
									liked={item.liked}
								/>
							),
						)
					) : (
						<></>
					)}
				</div>
			</div>
		</div>
	)
}

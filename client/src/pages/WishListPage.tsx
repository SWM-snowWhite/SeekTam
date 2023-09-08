import React from 'react'
import { wishlistItems } from '../data/wishListItem'
import WishListItem from '../components/WishListItem'

type WishListPageProps = {
	id: number
	title: string
	image: string
	price: number
	like: boolean
}
export default function WishListPage() {
	return (
		<div className='w-auto m-0min-h-screen p-4'>
			<div className='max-w-screen-md mx-auto grid grid-cols-1 md:grid-cols-3 gap-4'>
				{wishlistItems.map((item: WishListPageProps) => (
					<WishListItem
						key={item.id}
						id={item.id}
						title={item.title}
						image={item.image}
						like={item.like}
					/>
				))}
			</div>
		</div>
	)
}

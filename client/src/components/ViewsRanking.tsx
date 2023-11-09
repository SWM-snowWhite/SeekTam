import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai'
import { useNavigate } from 'react-router-dom'

type ViewsRankingProps = {
	ranking: number
	foodId: number
	foodName: string
	calorie: number
	liked: boolean
}

export default function ViewsRanking({
	fetchKeywordSearch,
}: {
	fetchKeywordSearch: (keyword: string) => void
}) {
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const [rankingData, setRankingData] = useState<ViewsRankingProps[]>()
	const navigator = useNavigate()
	const getViewsRanking = () => {
		axios
			.get(`${SERVER_API_URL}/foods/search/ranking`, {
				withCredentials: true,
			})
			.then(res => {
				// 랭킹 기준으로 내림차순 정렬
				setRankingData(res.data)
			})
			.catch(err => console.log(err))
	}

	useEffect(() => {
		getViewsRanking()
	}, [])

	const likeFood = (id: number) => {
		axios
			.put(
				`${SERVER_API_URL}/member/like`,
				{
					foodId: id,
				},
				{
					withCredentials: true,
				},
			)
			.then(res => {
				setRankingData(
					rankingData?.map(item => {
						if (item.foodId === id) {
							item.liked = true
						}
						return item
					}),
				)
			})
			.catch(err => console.log(err))
	}

	const unLikeFood = (id: number) => {
		axios
			.delete(`${SERVER_API_URL}/member/unlike`, {
				data: {
					foodId: id,
				},
				withCredentials: true,
			})
			.then(res => {
				setRankingData(
					rankingData?.map(item => {
						if (item.foodId === id) {
							item.liked = false
						}
						return item
					}),
				)
			})
			.catch(err => console.log(err))
	}
	return (
		<div className='justify-center p-0 m-auto rounded-md shadow-md align-center w-450 mt-30 border-1 border-info '>
			<span className='flex ml-20 font-bold text-20 text-g900 my-15'>
				유저들이 많이 찾는 식품 🥗
			</span>
			<div className=''>
				<ul className='flex flex-col w-[100%] m-auto rounded-md'>
					{rankingData ? (
						rankingData.map(
							(item: ViewsRankingProps, idx: number) => (
								<div
									key={idx}
									className='flex justify-between w-[90%] ml-15 mt-10 rounded-md text-16 h-70 border-1 border-g200 items-center'
								>
									<div
										onClick={() => {
											navigator(
												'/detail?foodId=' + item.foodId,
											)
										}}
										className='flex flex-col cursor-pointer'
									>
										<span className='mb-5 ml-20 font-bold text-g900'>
											{item.foodName}
										</span>
										<span className='ml-20 text-g500'>
											{item.calorie} kcal
										</span>
									</div>
									<div className='flex items-center justify-end h-full m-10 w-30'>
										{item.liked ? (
											<AiFillHeart
												onClick={() =>
													unLikeFood(item.foodId)
												}
												size={24}
												className='text-main'
											/>
										) : (
											<AiOutlineHeart
												onClick={() =>
													likeFood(item.foodId)
												}
												size={24}
												className='text-main'
											/>
										)}
									</div>
								</div>
							),
						)
					) : (
						<></>
					)}
				</ul>
			</div>
		</div>
	)
}

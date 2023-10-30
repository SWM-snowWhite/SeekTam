import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai'

type ViewsRankingProps = {
	ranking: number
	foodName: string
	calories: number
	liked: boolean
}

export default function ViewsRanking({
	fetchKeywordSearch,
}: {
	fetchKeywordSearch: (keyword: string) => void
}) {
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const [rankingData, setRankingData] = useState<ViewsRankingProps[]>()
	const getViewsRanking = () => {
		axios
			.get(`http://localhost:8080/foods/search/ranking`, {
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
	return (
		<div className='justify-center p-0 m-auto rounded-md shadow-md align-center w-320 mt-30 border-1 border-info'>
			<span className='flex font-bold text-20 text-grey900 ml-15'>
				유저들이 많이 찾는 식품 🥗
			</span>
			<ul className='flex flex-col w-[80%] m-auto rounded-md'>
				{rankingData ? (
					rankingData.map((item: ViewsRankingProps, idx: number) => (
						<div
							key={idx}
							onClick={() => fetchKeywordSearch(item.foodName)}
							className='flex mt-10 rounded-sm bg-sub text-16 h-70'
						>
							<div className='flex flex-col'>
								<span className='text-[#0E6C57] ml-10 font-bold'>
									{item.foodName}
								</span>
								<span className='ml-20'>
									{item.calories} kcal
								</span>
							</div>
							{item.liked ? <AiFillHeart /> : <AiOutlineHeart />}
						</div>
					))
				) : (
					<></>
				)}
			</ul>
		</div>
	)
}

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
			<span className='flex ml-20 font-bold text-20 text-grey900 my-15'>
				유저들이 많이 찾는 식품 🥗
			</span>
			<ul className='flex flex-col w-[100%] m-auto rounded-md'>
				{rankingData ? (
					rankingData.map((item: ViewsRankingProps, idx: number) => (
						<div
							key={idx}
							onClick={() => fetchKeywordSearch(item.foodName)}
							className='flex w-[90%] ml-15 mt-10 rounded-md text-16 h-70 border-1 border-grey200 items-center'
						>
							<div className='flex flex-col'>
								<span className='ml-10 font-bold text-grey900'>
									{item.foodName}
								</span>
								<span className='ml-20 text-grey500'>
									{item.calories} kcal
								</span>
							</div>
							<div className='flex items-center justify-start m-0 '>
								{item.liked 
									? <AiFillHeart size={24} className='text-red' /> 
									: <AiOutlineHeart size={24} />}
							</div>
						</div>
					))
				) : (
					<></>
				)}
			</ul>
		</div>
	)
}

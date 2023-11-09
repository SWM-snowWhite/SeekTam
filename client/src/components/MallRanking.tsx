import axios from 'axios'
import React, { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'

type MallRankingProps = {
	ranking: number
	foodKeyword: string
}

export default function MallRanking({
	fetchNonOptionKeywordSearch,
}: {
	fetchNonOptionKeywordSearch: (keyword: string) => void
}) {
	const navigator = useNavigate()
	const rankingListRef = useRef<HTMLUListElement>(null)
	const [rankingData, setRankingData] = useState<MallRankingProps[]>()
	const [currentIdx, setCurrentIdx] = useState(0)
	const [rankingListStyle, setRankingListStyle] = useState<{
		transform?: string
	}>({ transform: 'translateY(50)px' })

	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		getMallRanking()
	}, [])

	const getMallRanking = () => {
		axios
			.get(`${SERVER_API_URL}/mall/ranking`, {
				withCredentials: true,
			})
			.then(res => {
				// 랭킹 기준으로 내림차순 정렬
				const sortedData: MallRankingProps[] = res.data.sort(
					(a: MallRankingProps, b: MallRankingProps) =>
						a.ranking - b.ranking,
				)
				setRankingData(sortedData)
			})
			.catch(err => console.log(`getMallRanking error 발생: ${err}`))
	}

	useEffect(() => {
		const rankingList = document.getElementsByClassName('ranking-ul')
		// currentIdx가 변경될 때마다 스타일 객체를 업데이트합니다.
		const heightOfElement = 50
		const translateY = -currentIdx * heightOfElement
		setRankingListStyle({
			transform: `translateY(${translateY}px)`,
		})
	}, [currentIdx])

	// 5초마다 스크롤 함수를 호출합니다.
	useEffect(() => {
		const scrollInterval = setInterval(() => {
			setCurrentIdx(prevIdx => (prevIdx + 1) % 10) // 10개 항목 순환
		}, 5000)

		return () => {
			clearInterval(scrollInterval)
		}
	}, [])

	return (
		<div className='relative justify-center m-auto mt-20 bg-white rounded-md shadow-md z-999 align-center w-450 border-1 border-info'>
			<span className='flex my-10 font-bold text-20 ml-15'>
				실시간 트렌드 식품 검색어 👑
			</span>
			<div className='flex justify-center w-full overflow-hidden h-70'>
				<div className='flex-row m-auto overflow-hidden w-[90%] h-50'>
					<ul
						ref={rankingListRef}
						style={rankingListStyle}
						className='flex flex-col justify-center m-auto overflow-hidden rounded-md ranking-ul'
					>
						{rankingData ? (
							rankingData.map(
								(item: MallRankingProps, idx: number) => (
									<li
										key={idx}
										onClick={() =>
											navigator(
												`/search?keyword=${item.foodKeyword}`,
											)
										}
										className='cursor-pointer bg-[#F4F4F4] text-16 rounded-md h-50 align-middle'
									>
										<div className='flex items-center h-full m-auto ml-10'>
											<span className='text-16 text-[#0E6C57] font-bold'>
												{item.ranking}
											</span>
											<span className='ml-20 font-bold text-18'>
												{item.foodKeyword}
											</span>
										</div>
									</li>
								),
							)
						) : (
							<></>
						)}
					</ul>
				</div>
			</div>
		</div>
	)
}

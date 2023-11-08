import axios from 'axios'
import React, { useEffect, useState } from 'react'
import FoodInfoComponent from '../components/FoodInfoComponent'
import Range from '../components/Range'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
export type FoodInfoType = {
	foodId: number
	foodName: number
	calorie: number
	protein: number
	fat: number
	carbohydrate: number
	totalSizeG: number
	totalSizeMl: number
	companyName: string
	totalSugar: number
	imageUrl: string
	servingUnit: string
}

export default function FoodDetail() {
	const [foodInfo, setFoodInfo] = React.useState<FoodInfoType>({
		foodId: 0,
		foodName: 0,
		calorie: 0,
		protein: 0,
		fat: 0,
		carbohydrate: 0,
		totalSizeG: 0,
		totalSizeMl: 0,
		companyName: '',
		totalSugar: 0,
		imageUrl: '',
		servingUnit: 'g',
	})
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const isInitialMount = React.useRef(true)
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'
	const [selectedFoodId, setSelectedFoodId] = useState<number>(-1)
	const [stateLike, setStateLike] = useState(false)
	const navigator = useNavigate()
	const dispatcher = useDispatch()
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		if (isInitialMount.current) {
			const url = new URL(window.location.href)
			const foodId = url.searchParams.get('foodId')

			console.log(`foodID: ${foodId}`)
			if (foodId) {
				setSelectedFoodId(Number(foodId))
				fetchFoodDetailByFoodId(Number(foodId))
			}
			isInitialMount.current = false
		}
	}, [])

	const fetchFoodDetailByFoodId = async (foodId: number) => {
		// 초기값일 경우 api 던지지 않음
		axios
			.get(`${SERVER_API_URL}/foods/search/detail?foodId=${foodId}`, {
				withCredentials: true,
			})
			.then(response => {
				setFoodInfo(response.data)
				console.log('success')
				console.log(`response.data: ${JSON.stringify(response.data)}`)
				console.log(`foodInfo: ${JSON.stringify(foodInfo)}`)
			})
			.catch(err =>
				console.log(`상품 상세 정보 받아오기가 실패하였습니다.`, err),
			)
	}

	const handleUnLikeFood = () => {
		axios
			.delete(`${REACT_APP_SERVER_API_URL}/member/unlike`, {
				data: {
					foodId: foodInfo.foodId,
				},
				withCredentials: true,
			})
			.then(_ => {
				setStateLike(prevStateLike => !prevStateLike)
			})
			.catch(_ => {
				console.log('fail')
			})
	}

	const handleLikeFood = () => {
		axios
			.put(
				`${REACT_APP_SERVER_API_URL}/member/like`,
				{ foodId: foodInfo.foodId },
				{ withCredentials: true },
			)
			.then(_ => {
				setStateLike(prevStateLike => !prevStateLike)
			})
			.catch(_ => {
				console.log('fail')
			})

		setStateLike(!stateLike)
	}

	const handlePurchaseClick = () => {
		console.log(`food: ${foodInfo.foodId}`)
		window.location.href = `https://www.coupang.com/np/search?component=&q=${foodInfo.foodName}&channel=user`
	}
	return (
		<div className='absolute h-full p-4 m-0 bg-white w-500'>
			<div>
				<div className='flex flex-col justify-center w-auto mb-10 h-50'>
					<h1 className='font-bold m-15 text-20'>식품 상세 정보</h1>
					<hr className='text-grey200'></hr>
				</div>
			</div>
			<div className='flex-col items-center justify-center m-auto mt-20 w-500 h-270'>
				<img
					src={DEFAULT_IMAGE}
					alt='식품 이미지'
					className='m-auto bg-black rounded-2xl w-200 h-200'
				/>
				<h1 className='m-auto mt-20 font-bold text-center'>
					{foodInfo.foodName ? foodInfo.foodName : '진라면'}
				</h1>
			</div>
			<hr className='text-grey200'></hr>
			<div className='flex justify-around mt-20'>
				<div className='flex items-center justify-center rounded-md bg-grey100 w-120 h-30'>
					<span className='text-grey600'>
						기준량 | {foodInfo.totalSizeG} {foodInfo.servingUnit}
					</span>
				</div>
				<div className='flex items-center justify-center h-30'>
					<span className='text-grey500 text-14'>
						성인 하루 섭취량 기준
					</span>
				</div>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>열량</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>탄수화물</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>단백질</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around '>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex justify-around'>
					<div className='w-150'>지방</div>
					<Range value={foodInfo.calorie} max={100} />
				</div>
				<div className='flex justify-around'>
					<span>{foodInfo.calorie} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-grey200'></hr>
			</div>

			<hr className='text-grey200'></hr>
			<hr className='text-grey200'></hr>
			<hr className='text-grey200'></hr>
			<div className='flex items-center justify-center '>
				<button
					className='text-white rounded-lg h-50 w-400 bg-p800'
					onClick={() => handlePurchaseClick()}
				>
					구매하러 가기
				</button>
				<div className='h-50 flex w-[15%] justify-center items-center bg-grey800 rounded-lg m-auto my-5'>
					{stateLike ? (
						<AiFillHeart
							onClick={handleUnLikeFood}
							className='text-red mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiFillHeart>
					) : (
						<AiOutlineHeart
							onClick={handleLikeFood}
							className='text-grey100 mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiOutlineHeart>
					)}
				</div>
			</div>
		</div>
	)
}

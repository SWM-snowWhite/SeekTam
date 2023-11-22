import axios from 'axios'
import React, { useEffect, useState } from 'react'
import FoodInfoComponent from '../components/FoodInfoComponent'
import Range from '../components/Range'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import Navigator from '../components/Navigator'

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
	servingSize: number
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
		servingSize: 0,
		servingUnit: 'g',
	})
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
	const isInitialMount = React.useRef(true)
	const DEFAULT_IMAGE = '/images/Graphic/2x/food@2x.png'
	const [selectedFoodId, setSelectedFoodId] = useState<number>(-1)
	const [stateLike, setStateLike] = useState(false)
	const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	useEffect(() => {
		if (isInitialMount.current) {
			const url = new URL(window.location.href)
			const foodId = url.searchParams.get('foodId')

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
		window.location.href = `https://www.coupang.com/np/search?component=&q=${foodInfo.foodName}&channel=user`
	}
	return (
		<div className='absolute  flex-col h-[100vh] bg-white w-500 overflow-scroll'>
			<Navigator title={'식품 상세정보'} />
			<div className='flex-col items-center justify-center mt-70 w-500 h-270'>
				<img
					src={DEFAULT_IMAGE}
					alt='식품 이미지'
					className='m-auto rounded-2xl w-200 h-200'
				/>
				<h1 className='m-auto mt-10 text-center text-14 text-g400'>
					{foodInfo.companyName ? foodInfo.companyName : '없음'}
				</h1>
				<h1 className='m-auto mt-10 font-bold text-center'>
					{foodInfo.foodName ? foodInfo.foodName : '진라면'}
				</h1>
				<hr className='mt-10 text-g200'></hr>
			</div>
			<div className='flex items-center justify-between m-auto mt-20 w-420'>
				<div className='flex items-center justify-center rounded-md bg-g100 w-100 h-30'>
					<span className='text-g600 text-14'>
						기준량 | {foodInfo.servingSize}
						{foodInfo.servingUnit}
					</span>
				</div>
				<div className='flex items-center justify-center h-30'>
					<span className='text-g500 text-14'>
						성인 하루 섭취량 기준
					</span>
				</div>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420 '>
					<div className='flex ml-20 w-100'>열량</div>
					<Range value={foodInfo.calorie} max={2150} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.calorie} kcal</span>
					<span>{2150} kcal</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420'>
					<div className='flex ml-20 w-100'>탄수화물</div>
					<Range value={foodInfo.carbohydrate} max={324} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.carbohydrate} g</span>
					<span>{324} g</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420'>
					<div className='flex ml-20 w-100'>단백질</div>
					<Range value={foodInfo.protein} max={55} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.protein} g</span>
					<span>{55} g</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420'>
					<div className='flex ml-20 w-100'>지방</div>
					<Range value={foodInfo.fat} max={54} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.fat} g</span>
					<span>{54} g</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420'>
					<div className='flex ml-20 w-100'>포화지방</div>
					<Range value={foodInfo.fat} max={15} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.fat} g</span>
					<span>{15} g</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='my-20'>
				<div className='flex items-center m-auto w-420'>
					<div className='flex ml-20 w-100'>당류</div>
					<Range value={foodInfo.fat} max={100} />
				</div>
				<div className='flex justify-between my-5 ml-160 w-300 text-14'>
					<span>{foodInfo.fat} g</span>
					<span>{100} g</span>
				</div>
				<hr className='text-g200'></hr>
			</div>
			<div className='flex items-center justify-center w-full m-0 h-150'>
				<button
					className='ml-10 text-white rounded-lg h-50 w-400 bg-p900 text-20 mb-100'
					onClick={() => handlePurchaseClick()}
				>
					구매하러 가기
				</button>
				<div className='h-50 flex w-[15%] justify-center items-center bg-g800 rounded-lg m-auto mb-100'>
					{stateLike ? (
						<AiFillHeart
							onClick={handleUnLikeFood}
							className='text-red mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiFillHeart>
					) : (
						<AiOutlineHeart
							onClick={handleLikeFood}
							className='text-g100 mw-10 my-5 justify-self-center w-[50%] cursor-pointer'
							size={24}
						></AiOutlineHeart>
					)}
				</div>
			</div>
		</div>
	)
}

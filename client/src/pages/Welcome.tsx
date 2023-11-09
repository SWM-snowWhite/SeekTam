import React from 'react'
import CommonBtn from '../components/btn/CommonBtn'
import { useNavigate } from 'react-router-dom'

export default function Welcome() {
	const eatIcon = '/images/eat@2x.png'
	const navigate = useNavigate()

	return (
		<div className='absolute flex-row items-center justify-center h-full p-0 bg-white w-500'>
			<div className='w-500 h-200'></div>
			<h1 className='p-10 font-bold text-center mb-50 text-28 text-g800'>
				주형님, 반가워요!
			</h1>
			<img src={eatIcon} className='m-auto mb-50 w-200' />
			<h4 className='text-center mb-50 text-18 text-g800'>
				지금부터 식탐의 모든 서비스를 이용하실 수 있습니다!
			</h4>
			<CommonBtn
				title='시작하기'
				onClickAction={() => {
					navigate('/main')
				}}
			/>
		</div>
	)
}

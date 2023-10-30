import React from 'react'
import CommonBtn from '../components/btn/CommonBtn'
import { useNavigate } from 'react-router-dom'

export default function Welcome() {
	const eatIcon = '/images/eat@2x.png'
	const navigate = useNavigate()

	return (
		<div className='w-420'>
			<h1 className='text-28 font-bold text-grey800'>
				주형님, 반가워요!
			</h1>
			<img src={eatIcon} className='w-200' />
			<span className='text-20 text-grey800'>
				지금부터 식탐의 모든 서비스를 이용하실 수 있습니다!
			</span>
			<CommonBtn
				title='시작하기'
				onClickAction={() => {
					navigate('/keyword')
				}}
			/>
		</div>
	)
}

import React from 'react'
import { PiNotePencil } from 'react-icons/pi'
import { BsArrowRightCircle } from 'react-icons/bs'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import { useNavigate } from 'react-router-dom'
import Navigator from '../components/Navigator'

export default function Profile() {
	const { currentPage, userInfo } = useSelector((state: RootState) => state)
	const navigator = useNavigate()

	return (
		<div className='absolute h-full m-0 bg-white w-500'>
			<Navigator title='내 정보' />
			<div className='flex items-center mt-60'>
				<img
					src={
						userInfo.profileImageUrl
							? userInfo.profileImageUrl
							: 'https://www.shutterstock.com/shutterstock/photos/2097266809/display_1500/stock-photo-funny-british-shorthair-cat-portrait-looking-shocked-or-surprised-on-orange-background-with-copy-2097266809.jpg'
					}
					alt='프로필 사진'
					className='rounded-full m-15 w-106 h-106'
				/>
				<div className='flex-col items-center'>
					<h1 className='font-bold m-15 text-18'>
						{userInfo.nickname}
					</h1>
					<div
						onClick={() => alert('서비스 준비 중입니다.')}
						className='flex items-center justify-center m-auto rounded-full cursor-pointer w-120 h-37 bg-g100'
					>
						<PiNotePencil />
						<span className='font-medium'>수정하기</span>
					</div>
				</div>
			</div>
			<hr className='w-[90%] text-g200 m-14'></hr>
			<div className='flex flex-col justify-center m-15'>
				<h1 className='mb-24 font-bold text-20'>고객 지원</h1>
				<div className='text-16 text-g900 flex w-[90%] bg-blue justify-between mb-24'>
					<span className='font-medium'>서비스 이용약관</span>
					<BsArrowRightCircle
						onClick={() => navigator('/customer-support')}
						className='cursor-pointer text-g400'
						size={24}
					/>
				</div>
				<div className='text-16 text-g900 flex w-[90%] bg-blue justify-between mb-24'>
					<span className='font-medium'>개인정보 처리방침</span>
					<BsArrowRightCircle
						className='cursor-pointer text-g400'
						size={24}
					/>
				</div>
				<hr className='mb-14 text-g200 w-[90%]'></hr>
			</div>
			<div className='flex flex-col justify-center m-15'>
				<h1 className='mb-24 font-bold text-20'>계정 정보</h1>
				<div className='text-16 text-g900 flex w-[90%] bg-blue justify-between mb-24'>
					<span className='font-medium'>로그아웃</span>
					<BsArrowRightCircle
						className='cursor-pointer text-g400'
						size={24}
					/>
				</div>
				<div className='text-16 text-g900 flex w-[90%] bg-blue justify-between mb-24'>
					<span className='font-medium'>회원탈퇴</span>
					<BsArrowRightCircle
						className='cursor-pointer text-g400'
						size={24}
					/>
				</div>
			</div>
		</div>
	)
}

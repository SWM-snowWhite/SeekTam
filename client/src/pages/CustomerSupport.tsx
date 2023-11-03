import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import PrivacyPolicy from '../components/PrivacyPolicy'
import AgreementUtilization from '../components/AgreementUtilization'
import { currentPageUpdate } from '../store/CurrentPageSlice'

export default function CustomerSupport() {
	const { currentPage, userInfo } = useSelector((state: RootState) => state)
	const dispatcher = useDispatch()

	return (
		<div className='absolute h-full bg-white w-500 m-0min-h-screen'>
			<div>
				<div className='flex flex-col justify-center w-auto mb-10 h-50'>
					<h1 className='m-15 font-bold text-20'>고객지원</h1>
					<hr className='text-grey200'></hr>
				</div>
			</div>
			<div className='flex justify-around m-0 w-full h-40 items-center'>
				<div
					onClick={() => dispatcher(currentPageUpdate('agreement'))}
					className='text-grey400 cursor-pointer'
				>
					서비스 이용약관
				</div>
				<div
					onClick={() => dispatcher(currentPageUpdate('privacy'))}
					className='text-grey400 cursor-pointer'
				>
					개인정보 처리방침
				</div>
			</div>
			<hr className='text-grey200 mt-5'></hr>
			<div className='bg-grey100 w-full h-full m-auto overflow-hidden'>
				{currentPage === 'privacy' ? (
					<PrivacyPolicy />
				) : (
					<AgreementUtilization />
				)}
			</div>
		</div>
	)
}

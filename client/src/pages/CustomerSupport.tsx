import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import PrivacyPolicy from '../components/PrivacyPolicy'
import AgreementUtilization from '../components/AgreementUtilization'
import { CurrentPageType, currentPageUpdate } from '../store/CurrentPageSlice'

export default function CustomerSupport() {
	const dispatcher = useDispatch()
	const currentPage: CurrentPageType = useSelector(
		(state: RootState) => state.currentPage,
	)

	useEffect(() => {
		console.log(`currentPage: ${currentPage}`)
	}, [currentPage])
	return (
		<div className='absolute bg-white w-500 m-0 overflow-y-scroll h-[100vh]'>
			<div>
				<div className='flex flex-col justify-center w-auto mb-10 h-50'>
					<h1 className='m-15 font-bold text-20'>고객지원</h1>
					<hr className='text-grey200'></hr>
				</div>
			</div>
			<div className='flex justify-around m-0 w-full h-40 items-center'>
				<div
					onClick={() => dispatcher(currentPageUpdate('agreement'))}
					className={
						currentPage === 'agreement'
							? 'text-p800 cursor-pointer'
							: 'text-grey400 cursor-pointer'
					}
				>
					서비스 이용약관
				</div>
				<div
					onClick={() => dispatcher(currentPageUpdate('privacy'))}
					className={
						currentPage === 'privacy'
							? 'text-p800 cursor-pointer'
							: 'text-grey400 cursor-pointer'
					}
				>
					개인정보 처리방침
				</div>
			</div>
			<div className='flex justify-center'>
				<hr
					className={
						currentPage === 'agreement'
							? 'text-p800 mt-5 w-[50%] border-1 border-p800'
							: 'text-grey200 mt-5 w-[50%]'
					}
				></hr>
				<hr
					className={
						currentPage === 'privacy'
							? 'text-p800 mt-5 w-[50%] border-1 border-p800'
							: 'text-grey200 mt-5 w-[50%]'
					}
				></hr>
			</div>
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

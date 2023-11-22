import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import PrivacyPolicy from '../components/PrivacyPolicy'
import AgreementUtilization from '../components/AgreementUtilization'
import { CurrentPageType, currentPageUpdate } from '../store/CurrentPageSlice'
import Navigator from '../components/Navigator'

export default function CustomerSupport() {
	const dispatcher = useDispatch()
	const currentPage: CurrentPageType = useSelector(
		(state: RootState) => state.currentPage,
	)

	return (
		<div className='absolute bg-white w-500 m-0 overflow-y-scroll h-[100vh]'>
			<div>
				<Navigator title='고객지원' />
			</div>
			<div className='flex items-center justify-around w-full h-40 m-0'>
				<div
					onClick={() => dispatcher(currentPageUpdate('agreement'))}
					className={
						currentPage === 'agreement'
							? 'text-p800 cursor-pointer'
							: 'text-g400 cursor-pointer'
					}
				>
					서비스 이용약관
				</div>
				<div
					onClick={() => dispatcher(currentPageUpdate('privacy'))}
					className={
						currentPage === 'privacy'
							? 'text-p800 cursor-pointer'
							: 'text-g400 cursor-pointer'
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
							: 'text-g200 mt-5 w-[50%]'
					}
				></hr>
				<hr
					className={
						currentPage === 'privacy'
							? 'text-p800 mt-5 w-[50%] border-1 border-p800'
							: 'text-g200 mt-5 w-[50%]'
					}
				></hr>
			</div>
			<div className='w-full h-full m-auto overflow-hidden bg-g100'>
				{currentPage === 'privacy' ? (
					<PrivacyPolicy />
				) : (
					<AgreementUtilization />
				)}
			</div>
		</div>
	)
}

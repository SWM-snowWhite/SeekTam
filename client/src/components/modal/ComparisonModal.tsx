import React, { useState } from 'react'
import { FoodListType, FoodType } from '../../pages/Main'
import { RxReload } from 'react-icons/rx'
import { GiCancel } from 'react-icons/gi'
import { IoIosArrowUp, IoIosArrowDown } from 'react-icons/io'
import ComparisonFood from '../ComparisonFood'

export default function ComparisonModal({
	comparisonList,
	clearComparison,
	deleteSpecificComparison,
	handleComparisonView,
}: {
	comparisonList: FoodListType
	clearComparison: () => void
	deleteSpecificComparison: (idx: number) => void
	handleComparisonView: () => void
}) {
	const [isOpenModal, setIsOpenModal] = useState(true)

	const handleOpenModal = () => {
		setIsOpenModal(!isOpenModal)
	}

	const openComparisonView = () => {
		setIsOpenModal(false)
		handleComparisonView()
	}

	return (
		<div className='fixed w-500 bottom-64 flex-row items-center justify-center overflow-scroll bg-white rounded-md shadow-2xl cursor-pointer border-grey300'>
			{isOpenModal ? (
				<div>
					<div
						onClick={handleOpenModal}
						className='w-500 flex items-center justify-center h-40 m-auto rounded-md border-1 border-grey300'
					>
						<span className='mx-10 font-bold text-grey600'>
							식품 비교 닫기
						</span>
						<IoIosArrowDown
							size={24}
							className='rounded-none cursor-pointer text-info_s'
						/>
					</div>
					<div>
						<div
							onClick={clearComparison}
							className='flex justify-end mx-10 mt-10 cursor-pointer'
						>
							<RxReload size={16} className='mx-5 text-info_s' />
							<span className='font-semibold text-info_s text-12'>
								초기화
							</span>
						</div>
					</div>
					{comparisonList && (
						<div className='flex h-160 w-500 bottom-64'>
							{comparisonList.map((comparisonItem: FoodType) => (
								<ComparisonFood
									comparisonItem={comparisonItem}
									deleteSpecificComparison={
										deleteSpecificComparison
									}
								/>
							))}
						</div>
					)}
					<div
						onClick={openComparisonView}
						className='m-auto bottom-64 cursor-pointer flex items-center justify-center rounded-[10px] bg-p1000 w-350 h-40 text-white text-20 '
					>
						<span>비교하기</span>
					</div>
				</div>
			) : (
				<div
					className='border-1 border-grey200 bottom-64 bg-white flex items-center justify-center m-auto transform shadow-lg w-500 h-30'
					onClick={handleOpenModal}
				>
					<span className='mx-10 font-bold text-grey600 '>
						식품 비교하기
					</span>
					<IoIosArrowUp
						size={24}
						className='mx-10 rounded-none cursor-pointer text-info_s'
					/>
				</div>
			)}
		</div>
	)
}

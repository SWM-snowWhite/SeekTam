import React, { useState } from 'react'
import { RxReload } from 'react-icons/rx'
import { IoIosArrowUp, IoIosArrowDown } from 'react-icons/io'
import ComparisonFood from '../ComparisonFood'
import { useDispatch, useSelector } from 'react-redux'
import { resetComparisonFood } from '../../store/ComparisonSlice'
import { RootState } from '../..'
import { FoodType } from '../../pages/Search'

export default function ComparisonModal({
	handleComparisonView,
}: {
	handleComparisonView: () => void
}) {
	const [isOpenModal, setIsOpenModal] = useState(true)
	const dispatcher = useDispatch()
	const handleOpenModal = () => {
		setIsOpenModal(!isOpenModal)
	}
	const comparisonFood = useSelector(
		(state: RootState) => state.comparisonFood,
	)
	const openComparisonView = () => {
		setIsOpenModal(false)
		handleComparisonView()
	}

	return (
		<div className='fixed flex-row items-center justify-center bg-white rounded-md shadow-2xl cursor-pointer w-500 bottom-64 border-g300'>
			{isOpenModal ? (
				<div className=''>
					<div
						onClick={handleOpenModal}
						className='flex items-center justify-center h-40 m-auto rounded-md w-500 border-1 border-g300'
					>
						<span className='mx-10 font-bold text-g600'>
							식품 비교 닫기
						</span>
						<IoIosArrowDown
							size={24}
							className='rounded-none cursor-pointer text-info_s'
						/>
					</div>
					<div className=''>
						<div
							onClick={() => dispatcher(resetComparisonFood())}
							className='flex justify-end mx-10 mt-10 cursor-pointer'
						>
							<RxReload size={16} className='mx-5 text-info_s' />
							<span className='font-semibold text-info_s text-12'>
								초기화
							</span>
						</div>
					</div>
					<div>
						{comparisonFood && (
							<div className='flex h-160 w-500 bottom-64'>
								{comparisonFood.map((food: FoodType) => (
									<ComparisonFood food={food} />
								))}
							</div>
						)}
					</div>
					<div
						onClick={openComparisonView}
						className='cursor-pointer flex items-center justify-center rounded-[10px] bg-p1000 w-350 h-40 text-white text-20 '
					>
						<span>비교하기</span>
					</div>
				</div>
			) : (
				<div
					className='flex items-center justify-center m-auto transform bg-white shadow-lg border-1 border-g200 bottom-64 w-500 h-30'
					onClick={handleOpenModal}
				>
					<span className='mx-10 font-bold text-g600 '>
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

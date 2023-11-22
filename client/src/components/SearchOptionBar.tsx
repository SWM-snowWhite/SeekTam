import React, { useState } from 'react'
import SearchOption from './SearchOption'
import { MdKeyboardArrowDown, MdKeyboardArrowUp } from 'react-icons/md'
import SearchOption2 from './SearchOption'
import { SearchTitleTypeEng, SearchTitleTypeKor } from '../pages/Search'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import {
	SearchCondition,
	updateSearchOptionHandlerOpenState,
	updateSearchOptionOpenState,
} from '../store/SearchInfoSlice'
import SearchMiniOptionComp from './SearchMiniOptionComp'

export default function SearchOptionBar({
	searchOptionList,
}: {
	searchOptionList: any
}) {
	const [isOpen, setIsOpen] = useState(false)
	const [selectedOption, setSelectedOption] = useState<SearchTitleTypeKor>()
	const {
		isOptionOpened,
		isSearchOn,
		searchConditions,
		isOptionHandlerOpened,
	} = useSelector((state: RootState) => state.searchInfo)
	const dispatcher = useDispatch()
	const toggleDropdown = () => {
		dispatcher(updateSearchOptionOpenState(!isOptionOpened))
	}

	const handleOptionClick = (option: SearchTitleTypeKor) => {
		setSelectedOption(option)
		dispatcher(updateSearchOptionOpenState(false))
		dispatcher(updateSearchOptionHandlerOpenState(true))
	}

	return (
		<div className='flex-col items-center justify-around w-full ml-10 cursor-pointer '>
			<div className='flex items-center '>
				<div
					onClick={toggleDropdown}
					className='flex items-center justify-center h-40 rounded-md bg-g100 w-120 focus:outline-visible focus:border-1 focus:border-p900'
				>
					<h1>검색옵션</h1>
					{isOptionOpened ? (
						<MdKeyboardArrowUp size={24} />
					) : (
						<MdKeyboardArrowDown size={24} />
					)}
				</div>
				<div className='flex items-center justify-center'>
					{searchConditions &&
						searchConditions.map(
							(searchCondition: SearchCondition, idx: number) => {
								return (
									<SearchMiniOptionComp
										key={idx}
										searchCondition={searchCondition}
									/>
								)
							},
						)}
				</div>
			</div>

			{isOptionOpened && (
				<div className='absolute flex-col items-center justify-center m-auto text-center shadow-md w-120 black'>
					<div
						onClick={() => handleOptionClick('열량')}
						className='bg-white h-30 hover:rounded-sm hover:bg-g200'
					>
						열량
					</div>
					<div
						onClick={() => handleOptionClick('탄수화물')}
						className='bg-white h-30 hover:rounded-sm hover:bg-g200'
					>
						탄수화물
					</div>
					<div
						onClick={() => handleOptionClick('단백질')}
						className='bg-white h-30 hover:rounded-sm hover:bg-g200'
					>
						단백질
					</div>
					<div
						onClick={() => handleOptionClick('지방')}
						className='bg-white h-30 hover:rounded-sm hover:bg-g200'
					>
						지방
					</div>
				</div>
			)}
			{selectedOption && isOptionHandlerOpened && (
				<SearchOption selectedOption={selectedOption} />
			)}
		</div>
	)
}

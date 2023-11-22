import React, { useEffect, useState } from 'react'
import { SearchTitleTypeKor } from '../pages/Search'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '../index'
import {
	updateSearchCondition,
	updateSearchOptionHandlerOpenState,
} from '../store/SearchInfoSlice'

export default function SearchOption({
	selectedOption,
}: {
	selectedOption: SearchTitleTypeKor
}) {
	const [contentUpDown, setContentUpDown] = useState<number>(-1)
	const [content, setContent] = useState(-1)
	const { isOptionOpened, isSearchOn, searchConditions } = useSelector(
		(state: RootState) => state.searchInfo,
	)
	const dispatcher = useDispatch()

	useEffect(() => {}, [isOptionOpened, isSearchOn, searchConditions])

	const handleContentUpDownClick = (btnType: number) => {
		if (btnType === 1) {
			if (contentUpDown !== 1) {
				setContentUpDown(1)
			} else {
				setContentUpDown(-1)
			}
		}

		if (btnType === 0) {
			if (contentUpDown !== 0) {
				setContentUpDown(0)
			} else {
				setContentUpDown(-1)
			}
		}
	}

	const handleContentChange = (
		event: React.ChangeEvent<HTMLInputElement>,
	) => {
		const value = event.target.value

		// 입력된 값이 숫자인지 체크합니다. 정규식 /^[0-9]*$/는 0에서 9까지의 숫자만을 허용합니다.
		if (/^[0-9]*$/.test(value)) {
			setContent(Number(value))
		} else {
			// 숫자가 아닌 다른 값이 입력된 경우 alert를 띄웁니다.
			alert('숫자만 입력해주세요.')
		}
	}

	const handleApplyBtnClick = () => {
		console.log(`handleApplyBtnClick`)
		if (content === -1) {
			alert('함량을 입력해주세요')
			return
		}

		if (contentUpDown === -1) {
			alert('이상/이하를 선택해주세요')
			return
		}

		const tmpDict = {
			krName: selectedOption,
			content: content,
			contentUpDown: contentUpDown,
		}

		if (searchConditions) {
			dispatcher(updateSearchCondition([...searchConditions, tmpDict]))
		} else {
			dispatcher(updateSearchCondition([tmpDict]))
		}
		dispatcher(updateSearchOptionHandlerOpenState(false))
	}
	return (
		<div className='flex-col items-center mt-10 rounded-md border-1 border-g200 w-450 h-170'>
			<div className='flex items-center m-10 font-bold text-18'>
				<h1 className=''>{selectedOption}</h1>
			</div>
			<div className='flex items-center justify-between ml-10 rounded-md h-50 w-430 bg-g100'>
				<input
					onChange={handleContentChange}
					className='ml-10 rounded-md focus:outline-none bg-g100'
					placeholder='함량을 숫자로 입력해주세요'
					value={content === -1 ? '' : content}
				></input>
				<div className=''>
					<button
						onClick={() => handleContentUpDownClick(1)}
						className={
							contentUpDown === 1
								? 'h-32 mr-10 bg-p100 rounded-md w-70 border-1 border-p800 text-p800'
								: 'h-32 mr-10 bg-white rounded-md w-70 border-1 border-g200 text-g900'
						}
					>
						⬆️ 이상
					</button>
					<button
						onClick={() => handleContentUpDownClick(0)}
						className={
							contentUpDown === 0
								? 'h-32 mr-10 bg-p100 rounded-md w-70 border-1 border-p800 text-p800'
								: 'h-32 mr-10 bg-white rounded-md w-70 border-1 border-g200 text-g900'
						}
					>
						⬇️ 이하
					</button>
				</div>
			</div>
			<div className='flex items-center justify-center mt-10 h-50 text-18'>
				<button
					onClick={handleApplyBtnClick}
					className='h-40 text-white rounded-md w-430 bg-p800'
				>
					적용
				</button>
			</div>
		</div>
	)
}

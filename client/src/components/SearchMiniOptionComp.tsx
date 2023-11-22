import React from 'react'
import {
	SearchCondition,
	updateSearchCondition,
} from '../store/SearchInfoSlice'
import { useDispatch, useSelector } from 'react-redux'
import { RootState } from '..'
import { SearchTitleTypeKor } from '../pages/Search'

interface SearchMiniOptionCompProps {
	searchCondition: SearchCondition
}

export default function SearchMiniOptionComp({
	searchCondition,
}: SearchMiniOptionCompProps) {
	// 삭제 아이콘의 표시 상태를 관리하는 로컬 상태
	const [isHovering, setIsHovering] = React.useState(false)
	const dispathcer = useDispatch()
	const searchConditions = useSelector(
		(state: RootState) => state.searchInfo.searchConditions,
	)
	// 마우스 오버 상태를 설정하는 핸들러
	const handleMouseEnter = () => setIsHovering(true)
	const handleMouseLeave = () => setIsHovering(false)

	// 삭제 함수를 실행하는 핸들러
	const handleRemoveClick = () => {
		onRemove(searchCondition.krName)
	}

	const onRemove = (name: SearchTitleTypeKor) => {
		const filtered = searchConditions.filter(
			(v: SearchCondition) => v.krName !== name,
		)
		dispathcer(updateSearchCondition(filtered))
	}

	return (
		<div
			className='relative flex items-center justify-center h-40 mx-5 w-80 bg-p800 rounded-xl'
			onMouseEnter={handleMouseEnter}
			onMouseLeave={handleMouseLeave}
		>
			<span className='text-white'>{searchCondition.krName}</span>
			{isHovering && (
				<div
					className='absolute text-white cursor-pointer top-7 right-3'
					onClick={handleRemoveClick}
				>
					X
				</div>
			)}
		</div>
	)
}

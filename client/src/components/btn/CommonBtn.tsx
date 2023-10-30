import React from 'react'

export default function CommonBtn({
	title,
	onClickAction,
}: {
	title: string
	onClickAction: () => void
}) {
	return (
		<div
			className='cursor-pointer flex justify-center items-center border-r-100 bg-main w-335 h-50 text-white'
			onClick={onClickAction}
		>
			{title}
		</div>
	)
}

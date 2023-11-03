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
			className=' flex items-center justify-center m-auto text-white rounded-md cursor-pointer bottom-[20%] bg-main w-335 h-50'
			onClick={onClickAction}
		>
			{title}
		</div>
	)
}

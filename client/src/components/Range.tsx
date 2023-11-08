import React from 'react'
export default function Range({ value, max }: { value: number; max: number }) {
	const width = `${(value / max) * 100}%`
	return (
		<div className='flex justify-start p-0 m-0 rounded-full w-350 bg-grey200 border-1 border-grey200 h-30'>
			<div
				className='w-full mb-4 rounded-full h-30 w-300 bg-p900 '
				style={{ width }}
			></div>
			<div className='h-4 rounded-full bg-p100'></div>
		</div>
	)
}

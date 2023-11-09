import React from 'react'
export default function Range({ value, max }: { value: number; max: number }) {
	const width = `${
		(value / max) * 100 < 5 && (value / max) * 100 > 0
			? 5
			: (value / max) * 100
	}%`
	return (
		<div className='flex justify-start rounded-full w-300 bg-g200 border-1  border-g200 h-30'>
			<div
				className='mb-4 rounded-full h-30 bg-p800 '
				style={{ width }}
			></div>
			<div className='h-4 rounded-full bg-p100'></div>
		</div>
	)
}

import React from 'react'
import { AiOutlineArrowLeft } from 'react-icons/ai'
import { useNavigate } from 'react-router-dom'

export default function Navigator({ title }: { title: string }) {
	const navigate = useNavigate()
	const onBackClick = () => {
		navigate(-1)
	}
	const onSearchClick = () => {}

	return (
		<div className='absolute flex-col bg-white w-500'>
			<div className='flex items-center w-full'>
				<AiOutlineArrowLeft
					onClick={onBackClick}
					className='ml-20'
					size={24}
				/>
				<div className='flex flex-col justify-center w-full h-50'>
					<h1 className='mr-40 font-bold text-center text-20'>
						{title}
					</h1>
				</div>
			</div>
			<hr className='text-grey200'></hr>
		</div>
	)
}

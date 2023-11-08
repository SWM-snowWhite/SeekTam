import React from 'react'
import { FiArrowLeftCircle } from 'react-icons/fi'
import { Link, useNavigate } from 'react-router-dom'

export default function NavigatorExceptSearch() {
	const navigate = useNavigate()
	const LOGO_ORIGIN = './images/Logo/2x/Logo_full_origin@2x.png'
	const LOGO_WHITE = './images/Logo/2x/Logo_full_white@2x.png'
	const handleClick = () => {
		navigate(-1)
	}
	return (
		<div className='absolute flex justify-center w-500 h-220 bg-p900'>
			<Link to='/main'>
				<button className='mt-30'>
					<img className='w-100' src={LOGO_WHITE} />
				</button>
			</Link>
			<div className='ml-10'></div>
		</div>
	)
}

import React from 'react'
import { FiArrowLeftCircle } from 'react-icons/fi'
import { Link, useNavigate } from 'react-router-dom'

export default function NavigatorExceptSearch() {
	const navigate = useNavigate()
	const logo = './images/Logo/2x/Logo_full_origin@2x.png'
	const handleClick = () => {
		navigate(-1)
	}
	return (
		<div className='flex justify-center w-full p-0 m-0 h-100 bg-grey300'>
			<Link to='/'>
				<button className='mt-30'>
					<img className='w-100' src={logo} />
				</button>
			</Link>
			<div className='ml-10'></div>
		</div>
	)
}

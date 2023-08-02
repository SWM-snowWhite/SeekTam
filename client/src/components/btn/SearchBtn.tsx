import React from 'react'
import { AiOutlineSearch } from 'react-icons/ai'
import { BiBarcode } from 'react-icons/bi'
import { Link } from 'react-router-dom'
import "../../tailwind.css";
type IconType = string | 'search' | 'barcode'
type SearchButtonProps = {
	type: IconType
	title: string
}

export default function SearchBtn(props: SearchButtonProps): JSX.Element {
	return (
		<div className="flex items-center justify-center w-full p-5">
			<Link to={props.type === 'barcode' ? '/barcode' : '/keyword'}>
				<button className='flex items-center border-solid rounded-2xl justify-left w-320 h-65 border-main border-1'>
					{props.type === 'barcode' 
					? <BiBarcode className="m-30 text-main" size={50} /> 
					: <AiOutlineSearch className="m-30 text-main" size={50} />}
					<span className='m-30 text-main text-24 bold'>{props.title}</span>
				</button>
			</Link>
		</div>
	)
}
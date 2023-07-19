import React from 'react'
import { AiOutlineSearch } from 'react-icons/ai'
import { BiBarcode } from 'react-icons/bi'
import { Link } from 'react-router-dom'
import "../tailwind.css";
type IconType = string | 'search' | 'barcode'
type SearchButtonProps = {
	type: IconType
	title: string
}

export default function SearchButton(props: SearchButtonProps): JSX.Element {
	return (
		<div>
			<Link to={props.type === 'barcode' ? '/barcode' : '/keyword'}>
				<button className='absolute bg-main'>
					{props.type === 'barcode' ? <BiBarcode size={50} /> : <AiOutlineSearch size={50} />}
					<span className=''>{props.title}</span>
				</button>
			</Link>
		</div>
	)
}

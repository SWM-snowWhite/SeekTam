import React from 'react'
import "./tailwind.css";
import SearchButton from './components/SearchButton'

function App() {
	const logo = "/logo.png"
	const iconsInfo = [
		{
			type: 'keyword',
			title: '키워드 검색',
		},
		{
			type: 'barcode',
			title: '바코드 검색',
		},
	]

	return (
		<div className='flex-row h-full border-solid border-1 w-500 border-main'>
			{/* <Routes> */}
			<div className="flex justify-center w-full bg-black">
				<img src={logo} className="w-320"/>
			</div>
			<div className="w-320 flex bg-[#F9F9F9] justify-center mx-auto my-10 p-10 rounded-lg">
				<span className="font-bold text-14">알고싶은 성분을 [키워드 검색] <br/>또는 [바코드 검색]을 눌러 탐색 해 보세요!</span>
			</div>
			{iconsInfo.map(
				icon => (
						<SearchButton type={icon.type} title={icon.title} />
				))}
				</div>
	)
}

export default App

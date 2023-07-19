import React from 'react'
import "./tailwind.css";
import SearchButton from './components/SearchButton'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Keyword from './pages/Keyword'
import Barcode from './pages/Barcode'

function App() {
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
		<div>
			{/* <Routes> */}
        <text className="text-center w-1 text-white">알고싶은 성분을 검색어 입력 또는 바코드 촬영을 눌러 탐색 해 보세요!</text>
			{iconsInfo.map(
				icon => (
					// <Route path={`/${icon.type}`} element={icon.type === "keyword" ? <Keyword/> : <Barcode/>}>
					<SearchButton type={icon.type} title={icon.title} />
				),
				// </Route>
			)}
			{/* </Routes> */}
		</div>
	)
}

export default App

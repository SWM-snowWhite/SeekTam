import React from 'react'
import ReactDOM from 'react-dom'
import "./tailwind.css";
import App from './App'
import { BrowserRouter, Route, RouterProvider, Routes, createBrowserRouter } from 'react-router-dom'
import Keyword from './pages/Keyword'
import Barcode from './pages/Barcode'
import FoodsDetail from './pages/FoodsDetail';
import NotFound from './pages/NotFound';

const rootElement = document.getElementById('root');
ReactDOM.render(
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<App />} />
				<Route path="/keyword" element={<Keyword />} />
				<Route path="/barcode" element={<Barcode />} />
				<Route path="/foods/detail/*" element={<FoodsDetail />} />
				{/* <Route path="*" element={<NotFound />} /> */}
			</Routes>
    	</BrowserRouter>,rootElement
)

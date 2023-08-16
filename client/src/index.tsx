import React from 'react'
import ReactDOM from 'react-dom'
import "./tailwind.css";
import App from './App'
import { BrowserRouter, Route, RouterProvider, Routes, createBrowserRouter } from 'react-router-dom'
import Keyword from './pages/Keyword'

const rootElement = document.getElementById('root');
ReactDOM.render(
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<App />} />
				<Route path="/keyword" element={<Keyword />} />
			</Routes>
		</BrowserRouter>,rootElement
)

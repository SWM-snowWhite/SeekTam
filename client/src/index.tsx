import React from 'react'
import ReactDOM from 'react-dom/client'
import "./tailwind.css";
import App from './App'
import { BrowserRouter, Route, RouterProvider, Routes, createBrowserRouter } from 'react-router-dom'
import Keyword from './pages/Keyword'
import Barcode from './pages/Barcode'

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement)
root.render(
	<React.StrictMode>
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<App />} />
			</Routes>
		</BrowserRouter>
	</React.StrictMode>,
)

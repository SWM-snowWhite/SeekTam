import React from 'react'
import ReactDOM from 'react-dom'
import "./tailwind.css";
import App from './App'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Keyword from './pages/Keyword'
import Signup from './pages/Signup';
import WishListPage from './pages/WishListPage';

const rootElement = document.getElementById('root');
ReactDOM.render(
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<App />} />
				<Route path="/keyword" element={<Keyword />} />
				<Route path="/signup" element={<Signup />} />
				<Route path="/wishlist" element={<WishListPage />} />
			</Routes>
		</BrowserRouter>,rootElement
)

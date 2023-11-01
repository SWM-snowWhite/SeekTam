import React from 'react'
import ReactDOM from 'react-dom'
import './tailwind.css'
import App from './App'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Signup from './pages/Signup'
import WishListPage from './pages/WishList'
import Footer from './components/Footer'
import Welcome from './pages/Welcome'
import BackgroundLeft from './pages/BackgroundLeft'
import BackgroundRight from './pages/BackgroundRight'
import Search from './pages/Search'
import Main from './pages/Main'

const rootElement = document.getElementById('root')
ReactDOM.render(
	<BrowserRouter>
		<BackgroundLeft/>
		<BackgroundRight/>
		<Routes>
			<Route path='/' element={<App />} />
			<Route path='/main' element={<Main />} />
			<Route path='/signup' element={<Signup />} />
			<Route path='/wishlist' element={<WishListPage />} />
			<Route path='/welcome' element={<Welcome />} />
			<Route path='/search' element={<Search />} />
		</Routes>
		<Footer />
	</BrowserRouter>,
	rootElement,
)

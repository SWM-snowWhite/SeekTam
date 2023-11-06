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
import storage from 'redux-persist/lib/storage'
import { combineReducers } from 'redux'
import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit'

import { CurrentPageType } from './store/CurrentPageSlice'
import { UserInfo } from './store/UserInfoSlice'
import { persistReducer, persistStore } from 'redux-persist'
import { Provider } from 'react-redux'
import { PersistGate } from 'redux-persist/integration/react'
import CurrentPageSlice from './store/CurrentPageSlice'
import UserInfoSlice from './store/UserInfoSlice'
import Profile from './pages/Profile'
import CustomerSupport from './pages/CustomerSupport'
import FoodDetail from './pages/FoodDetail'
/* persist 선언 */
const persistConfig = {
	key: 'root',
	version: 1,
	storage,
}

/* reducer 세팅 */
/* reducer가 추가되면, 추가해 주세요. */
const reducers = combineReducers({
	currentPage: CurrentPageSlice,
	userInfo: UserInfoSlice,
})

/* persist reducer 세팅 (persistConfig가 추가된 reducer) */
const persistedReducer = persistReducer(persistConfig, reducers)

/* store 세팅 */
const store = configureStore({
	reducer: persistedReducer,
	middleware: getDefaultMiddleware =>
		getDefaultMiddleware({
			serializableCheck: false,
		}),
})

/* RootState Type 세팅 */
export interface RootState {
	userInfo: UserInfo
	currentPage: CurrentPageType
}

/* persist store 세팅 (새로고침, 종료해도 지속될 store) */
export const persistor = persistStore(store)

const rootElement = document.getElementById('root')
ReactDOM.render(
	<Provider store={store}>
		<React.StrictMode>
			<BrowserRouter>
				<PersistGate loading={null} persistor={persistor}></PersistGate>
				<BackgroundLeft />
				<BackgroundRight />
				<Routes>
					<Route path='/' element={<App />} />
					<Route path='/main' element={<Main />} />
					<Route path='/signup' element={<Signup />} />
					<Route path='/wishlist' element={<WishListPage />} />
					<Route path='/welcome' element={<Welcome />} />
					<Route path='/search' element={<Search />} />
					<Route path='/profile' element={<Profile />} />
					<Route path='/detail' element={<FoodDetail />} />
					<Route
						path='/customer-support'
						element={<CustomerSupport />}
					/>
				</Routes>
				<Footer />
			</BrowserRouter>
		</React.StrictMode>
	</Provider>,
	rootElement,
)

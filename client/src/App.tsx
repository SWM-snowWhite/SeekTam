import React, { useEffect, useRef } from 'react'
import './tailwind.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import SearchBtn from './components/btn/SearchBtn'

function App() {
	const navigate = useNavigate()
	// SDK는 한 번만 초기화해야 한다.
	const KAKAO = (window as any).Kakao
	const isInitialMount = useRef(true)
	const logo = '/logo.png'
	const REDIRECT_URI = process.env.REACT_APP_REDIRECT_URL
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	const kakaoLogin = () => {
		KAKAO.Auth.authorize({
			redirectUri: REDIRECT_URI,
		})
	}

	useEffect(() => {
		// 최초 렌더링 시 url에 있는 code값 전달
		if (isInitialMount.current) {
			const url = new URL(window.location.href)
			// authorization server로부터 클라이언트로 리디렉션된 경우, authorization code가 함께 전달
			const authorizationCode = url.searchParams.get('code')
			if (authorizationCode) {
				getAccessToken(authorizationCode)
			}
			isInitialMount.current = false
		}
	})

	const getAccessToken = async (authorizationCode: string) => {
		await setTimeout(() => {
			axios
				.post(
					`${SERVER_API_URL}/api/oauth/kakao`,
					{
						authorizationCode,
					},
					{
						withCredentials: true,
					},
				)
				.then(_ => {
					/**
					 * 서버에서 access token, refresh token을 쿠키로 받은 뒤,
					 * 회원가입 페이지로 전달
					 */
					// navigate('/signup')
					navigate('/signup')
				})
				.catch(error => {
					console.log(`Error: ${error}`)
					alert('회원가입에 실패하였습니다. 다시 시도하여 주시기 바랍니다.')
				})
		}, 1000)
	}
	return (
		<div className='flex-row h-full border-solid border-1 w-500 border-main'>
			<div className='flex justify-center m-auto'>
				<img src={logo} className='w-320' />
			</div>
			<button className='flex-row justify-center w-full m-auto align-center'>
				<img
					className='m-auto w-600 h-50'
					onClick={kakaoLogin}
					alt='카카오 로그인 버튼'
					src='/images/kakao_login_large_wide.png'
				/>
			</button>
			<SearchBtn type='keyword' title="키워드 검색"/>
		</div>
	)
}

export default App

import React, { useEffect, useRef, useState } from 'react'
import './tailwind.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import SearchBtn from './components/btn/SearchBtn'
import Footer from './components/Footer'

function App() {
	const navigate = useNavigate()
	// SDK는 한 번만 초기화해야 한다.
	const KAKAO = (window as any).Kakao
	const isInitialMount = useRef(true)
	const logo = '/images/Logo_symbol_origin@4x.png'
	const typo = '/images/Logo_typo_white@4x.png'
	const REDIRECT_URI = process.env.REACT_APP_REDIRECT_URL
	const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL

	const [memberInfo, setMemberInfo] = useState({})

	const kakaoLogin = () => {
		KAKAO.Auth.authorize({
			redirectUri: REDIRECT_URI,
		})
	}

	const login = () => {
		axios
			.post(`${process.env.REACT_APP_SERVER_API_URL}/member/login`, {
				memberInfo,
			})
			.then(response => {
				setMemberInfo(response.data)
				navigate('/main')
			})
			.catch(err => {
				console.log(err)
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
		login()
	})

	const getAccessToken = async (authorizationCode: string) => {
		axios
			.post(
				`${SERVER_API_URL}/api/oauth/kakao`,
				{ authorizationCode },
				{ withCredentials: true },
			)
			.then(response => {
				/**
				 * 서버에서 access token, refresh token을 쿠키로 받은 뒤,
				 * 200 status code를 반환하면 메인 페이지로 이동
				 * 3XX status code를 반환하면 회원가입 페이지로 이동
				 */
				console.log(`data: ${JSON.stringify(response.data)}`)
				response.status === 200
					? navigate('/keyword')
					: navigate('/main')
			})
			.catch(error => {
				console.log(`Error: ${error}`)
				alert(
					'회원가입에 실패하였습니다. 다시 시도하여 주시기 바랍니다.',
				)
			})
	}

	return (
		<div className='absolute flex-row h-full border-solid w-500 bg-main'>
			<div className='flex justify-center m-auto mb-150'>
				<div className='flex flex-col justify-center m-0'>
					<img src={logo} className='w-250' />
					<img src={typo} className='w-250' />
				</div>
			</div>
			<button className='flex-row justify-center w-full m-auto align-center'>
				<img
					className='m-auto w-335 h-50'
					onClick={kakaoLogin}
					alt='카카오 로그인 버튼'
					src='/images/kakao_login_large_wide.png'
				/>
			</button>
		</div>
	)
}

export default App

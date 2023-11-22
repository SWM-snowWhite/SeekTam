import React from 'react'

export default function FooterMain() {
	return (
		<div className='flex justify-center w-full h-[20%] my-20 bg-g100'>
			<div className='w-[80%] my-10'>
				<div className='flex flex-col justify-center w-auto'>
					<h1 className='text-18 text-bold text-g700'>(주)식탐</h1>
					<div className='flex my-5 text-16'>
						<h3 className='m-5 text-g500'>개발진</h3>
						<span className='m-5 text-g400'>
							임동훈 박주혁 한주형
						</span>
					</div>
					<div className='flex my-5 text-16'>
						<h3 className='m-5 text-g500'>이메일</h3>
						<span className='m-5 text-g400'>
							wngud4950@gmail.com
						</span>
					</div>
					<div className='flex my-5 text-16'>
						<h3 className='m-5 text-g500'>주소</h3>
						<span className='m-5 text-g400'>
							서울 강남구 테헤란로 311 아남타워빌딩 7층
						</span>
					</div>
					<span className='text-g300'>
						© Seektam Inc All Rights Reserved{' '}
					</span>
				</div>
			</div>
		</div>
	)
}

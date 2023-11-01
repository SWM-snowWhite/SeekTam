import React from 'react';

export default function FooterMain() {
    return (
        <div className='flex w-full my-20 bg-grey100'>
            <div className='flex flex-col justify-center w-auto'>
                <h1 className='text-20 text-bold text-grey700'>(주)식탐</h1>
                <div className='flex my-5 text-16'>
                    <h3 className='m-5 text-grey500'>개발진</h3>
                    <span className='m-5 text-grey400'>임동훈 박주혁 한주형</span>
                </div>
                <div className='flex my-5 text-16'>
                    <h3 className='m-5 text-grey500'>이메일</h3>
                    <span className='m-5 text-grey400'>wngud4950@gmail.com</span>
                </div>
                <div className='flex my-5 text-16'>
                    <h3 className='m-5 text-grey500'>주소</h3>
                    <span className='m-5 text-grey400'>서울 강남구 테헤란로 311 아남타워빌딩 7층</span>
                </div>
                <span className='text-grey300'>© Seektam Inc All Rights Reserved </span>
            </div>
        </div>
    );
}


import React from 'react';

export default function FodmapDetail() {
    return (
        <div className='flex-row justify-center m-auto'>
            <h1 className='m-10 font-bold text-14'>포드맵 상세 정보</h1>
            <div className='flex justify-around m-10 font-medium text-12 text-info_s'>
                <span>원재료명</span>
                <span>과당</span>
                <span>유당</span>
                <span>만니톨</span>
                <span>솔비톨</span>
                <span>GOS</span>
                <span>프락탄</span>
            </div>
            <div className='flex justify-around m-10 font-bold text-12'>
                <span>돼지고기</span>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
            </div>
            <div className='flex justify-around m-10 font-bold text-12'>
                <span>면</span>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
            </div>
        </div>
    );
}


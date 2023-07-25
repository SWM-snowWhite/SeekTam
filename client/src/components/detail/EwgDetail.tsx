import React from 'react';

export default function EwgDetail() {
    return (
        <div>
            <div className='flex justify-between m-10 text-12 text-info_s'>
                <span>등급</span>
                <span>성분명</span>
            </div>
            <div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                    <span className='font-bold text-12'>알파폴리겔</span>
                </div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-high_danger'></div>
                    <span className='font-bold text-12'>칠리혼합추출물</span>
                </div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                    <span className='font-bold text-12'>이스트엑기스3</span>
                </div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-low_danger'></div>
                    <span className='font-bold text-12'>포도당</span>
                </div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-good'></div>
                    <span className='font-bold text-12'>혼합제제</span>
                </div>
                <div className='flex justify-between border-b-1 border-[black] border-opacity-30 m-10'>
                    <div className='w-16 h-16 text-center rounded-full bg-good'></div>
                    <span className='font-bold text-12'>육수맛조미분</span>
                </div>
            </div>
        </div>
    );
}


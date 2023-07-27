import React from 'react';

export default function FodmapModal() {
    return (
        <div className='flex-row items-center justify-center m-auto'>
            <h1 className='m-auto font-bold text-center text-20 bg-[grey] bg-opacity-20 rounded-md mb-10'>포드맵 성분이란?</h1>
            <p className='font-bold text-14'>
            포드맵은 소화 과정에서 흡수되지 않고 남아 발효되는 화합물인 올리고당, 이당류, 단당류, 폴리올을 일컫는 약어입니다.
            </p>
            <div className='flex-row p-5 m-auto rounded-xl bg-info'>
                <p className='font-bold text-14'><span className='text-main'>F</span>ermentable <span className='text-main'>발효되기 쉬운</span></p>
                <p className='font-bold text-14'><span className='text-main'>O</span>ligo- <span className='text-main'>올리고당류</span></p>
                <p className='font-bold text-14'><span className='text-main'>D</span>i- <span className='text-main'>이당류</span></p>
                <p className='font-bold text-14'><span className='text-main'>M</span>ono-saccharides <span className='text-main'>단당류</span></p>
                <p className='font-bold text-14'><span className='text-main'>A</span>nd <span className='text-main'>그리고</span></p>
                <p className='font-bold text-14'><span className='text-main'>P</span>olyol <span className='text-main'>폴리올</span></p>
            </div>
            
            <p className='font-bold text-14'>
                이러한 포드맵 성분은 주로 탄수화물에 속하며, 과민성대장증후군 및 기타 위장 장애 증상을 일으킨다고 알려져 있습니다. 포드맵이 많이 함유된 일반적인 식품으로는 우유, 사과, 브로콜리, 밀가루빵, 시리얼, 익은 바나나, 파스타, 아보카도, 배, 버섯 등이 있습니다.
            </p>
        </div>
    );
}


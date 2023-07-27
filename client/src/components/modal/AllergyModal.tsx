import React from 'react';

export default function AllergyModal() {
    return (
        <div className='flex-row items-center justify-center m-auto'>
            <h1 className='m-auto font-bold text-center text-20 bg-[grey] bg-opacity-20 rounded-md mb-10'>식품 알레르기란?</h1>
            <p className='font-bold text-14'>
                일반적으로 무해한 식품을 특정인이 섭취했을 경우 그 식품에 대해 과도한 면역 반응이 일어나는 것으로,
            </p>
            <p className='font-bold text-14'>
                특정음식을 섭취할 때마다 반복되고 식품의 양과 관계없이 극소량으로도 생명에 위협이 되는 <span className='text-main'>아낙필락시스*(쇼크 등 생명을 잃을 수 있는 증상)</span> 등이 발생할 수 있는 위험이 있습니다.
            </p>
            <img className='rounded-[10px] my-10' src="/images/알러지.png"/>
        </div>
    );
}


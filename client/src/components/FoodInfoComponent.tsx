import React from 'react';
import { FoodInfoType } from '../pages/FoodsDetail';

export default function FoodInfoComponent({foodInfo}: {foodInfo: FoodInfoType}) {
    return (
        <div className='w-320 bg-info flex-row items-center m-auto'>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>식품명</span>
                <span className='border-1 rounded-md m-10 p-3'>{foodInfo.foodNm}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>에너지(kcal)</span>
                <span className='border-1 rounded-md m-10 p-3'>{foodInfo.enerc}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>영양성분함량 기준량</span>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>{foodInfo.nutConSrtrQua}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>탄수화물</span>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>{foodInfo.chocdf}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>단백질</span>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>{foodInfo.prot}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>지방</span>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>{foodInfo.fatce}</span>
            </div>
            <div>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>당류</span>
                <span className='border-1 rounded-md border-info_s m-10 p-3'>{foodInfo.sugar}</span>
            </div>
        </div>
    );
}


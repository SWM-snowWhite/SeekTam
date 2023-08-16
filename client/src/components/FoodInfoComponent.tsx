import React from 'react';
import { FoodInfoType } from '../pages/FoodsDetail';

export default function FoodInfoComponent({foodInfo}: {foodInfo: FoodInfoType}) {
    return (
        <div className='flex-row m-auto mt-20 w-150 text-14 text-main'>
            <div className='flex justify-between'>
                <span className='p-3 m-5 rounded-md border-1 border-sub'>에너지(kcal)</span>
                <span className='p-3 m-5 text-info_s'>{foodInfo.enerc ? foodInfo.enerc : 0}</span>
            </div>
            <div className='flex justify-between'>
                <span className='p-3 m-5 rounded-md border-1 border-sub'>탄수화물</span>
                <span className='p-3 m-5 text-info_s'>{foodInfo.chocdf ? foodInfo.chocdf : 0}g</span>
            </div>
            <div className='flex justify-between'>
                <span className='p-3 m-5 rounded-md border-1 border-sub'>단백질</span>
                <span className='p-3 m-5 text-info_s'>{foodInfo.prot ? foodInfo.prot : 0}g</span>
            </div>
            <div className='flex justify-between'>
                <span className='p-3 m-5 rounded-md border-1 border-sub'>지방</span>
                <span className='p-3 m-5 text-info_s'>{foodInfo.fatce ? foodInfo.fatce : 0}g</span>
            </div>
            <div className='flex justify-between'>
                <span className='p-3 m-5 rounded-md border-1 border-sub'>당류</span>
                <span className='p-3 m-5 text-info_s'>{foodInfo.sugar ? foodInfo.sugar : 0}g</span>
            </div>
        </div>
    );
}


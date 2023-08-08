import React from 'react';
import { FoodListType } from '../pages/Keyword';

export default function FoodList(
    {
        foodList,
        selectedKeyword,
        isSearched,
        handleSelectedFood
    }: {
        foodList: FoodListType,
        selectedKeyword: string,
        isSearched: boolean,
        handleSelectedFood: (foodId: number) => void
    }) {

    return (

        <div>
            {
                selectedKeyword !== "" 
                ? <div className='m-10'>
                    <span className='font-bold'>'{selectedKeyword}'</span>
                    <span className=''>에 대한 검색 결과</span>
                </div>
                :<></>
            }
            {
                foodList.length > 0
                ? foodList.map(foodItem => (
                    <div onClick={() => handleSelectedFood(foodItem.foodId)}className='flex justify-between m-10 rounded-lg cursor-pointer border-b-1 text-14 text-info_s hover:bg-info_s hover:bg-opacity-30'>
                        <span className='p-3 m-5'>{foodItem.foodName}</span>
                        <span className='p-3 m-5 rounded-lg bg-sub'>{foodItem.manufacture}</span>
                    </div>
                ))
                : isSearched ? <span className='flex justify-center mt-50'>검색 결과가 없습니다.</span>
                : <></>
            }
        </div>
    );
}


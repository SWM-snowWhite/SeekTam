import React from 'react';
import { FoodListType } from '../pages/Keyword';
import { Link } from 'react-router-dom';

export default function FoodList(
    {
        foodList,
        selectedKeyword,
        isSearched
    }: {
        foodList: FoodListType,
        selectedKeyword: string,
        isSearched: boolean
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
                    <Link to={`/foods/detail/${foodItem.foodId}`}>
                    <div className='flex justify-between m-10 rounded-lg cursor-pointer border-b-1 text-14 text-info_s hover:bg-info_s hover:bg-opacity-30'>
                        <span className='p-3 m-5'>{foodItem.foodName}</span>
                        <span className='p-3 m-5 rounded-lg bg-sub'>{foodItem.manufacture}</span>
                    </div>
                    </Link>
                ))
                : isSearched ? <span className='flex justify-center mt-50'>검색 결과가 없습니다.</span>
                : <></>
            }
        </div>
    );
}


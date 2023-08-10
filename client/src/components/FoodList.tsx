import React from 'react';
import { FoodListType, FoodType } from '../pages/Keyword';
import { BiMessageSquareAdd } from 'react-icons/bi';

export default function FoodList(
    {
        foodList,
        selectedKeyword,
        isSearched,
        handleSelectedFood,
        addComparison
    }: {
        foodList: FoodListType,
        selectedKeyword: string,
        isSearched: boolean,
        handleSelectedFood: (foodId: number) => void
        addComparison: (foodItem: FoodType) => void
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
                    <div className='flex justify-center'>
                        <div onClick={() => handleSelectedFood(foodItem.foodId)} className='flex justify-between m-10 rounded-lg cursor-pointer w-300 border-b-1 text-14 text-info_s hover:bg-info_s hover:bg-opacity-30'>
                            <span className='p-3 m-5'>{foodItem.foodName}</span>
                            <span className='p-3 m-5 rounded-lg bg-sub'>{foodItem.manufacture}</span>
                        </div>
                        <div onClick={() => addComparison(foodItem)} className='cursor-pointer flex items-center justify-around m-auto rounded-[10px] border-1 hover:bg-info hover:bg-opacity-20 border-info_s'>
                            <BiMessageSquareAdd size={16} className='mx-2 my-10 text-info_s'/>
                            <span className='mx-2 text-12 text-info_s'>비교함</span>
                        </div>
                    </div>
                    
                ))
                : isSearched ? <span className='flex justify-center mt-50'>검색 결과가 없습니다.</span>
                : <></>
            }
        </div>
    );
}


import React from 'react';
import { FoodListType, FoodType } from '../pages/Main';
import { BiMessageSquareAdd } from 'react-icons/bi';
import Food from './Food';
import NotFound from './NotFound';

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

        <div className='flex flex-col justify-between w-full h-full m-auto'>
            {
                selectedKeyword !== "" && 
                <div className='m-20 text-22'>
                    <span className='font-bold'>'{selectedKeyword}'</span>
                    <span className=''>에 대한 검색 결과</span>
                </div>
            }
            <div className='grid grid-cols-2 p-0 m-auto gap-y-40 gap-x-50'>
            {
                foodList.length > 0
                ? foodList.map((foodItem: FoodType) => (
                    <Food
                        key={foodItem.foodId}
                        foodItem={foodItem}
                        addComparison={addComparison}
                    />
                ))
                : !isSearched && <NotFound/>
            }
            </div>
        </div>
    );
}


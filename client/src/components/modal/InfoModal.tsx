import React from 'react';
import FoodsDetail from '../../pages/FoodsDetail';

export default function InfoModal(
    {
        selectedFoodIdx,
        handleSelectedFood,
    }: {
        selectedFoodIdx: number,
        handleSelectedFood: (foodIdx: number) => void
    }) {
    return (
        <div className='  h-full fixed bg-[black] w-390 bg-opacity-50 flex justify-center items-center'>
            <div className='border-3 border-main rounded-[10px] fixed bg-[white] w-310 h-546'>
                <FoodsDetail selectedFoodIdx={selectedFoodIdx}/>
                <button onClick={() => handleSelectedFood(-1)} className='flex justify-center m-auto my-10 rounded-[10px] w-270 h-30 bg-main text-[white] text-20 font-bold'>
                    확인
                </button>
            </div>
        </div>
    );
}


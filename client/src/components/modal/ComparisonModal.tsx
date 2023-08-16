import React, { useState } from 'react';
import { FoodListType, FoodType } from '../../pages/Keyword';
import { RxReload } from 'react-icons/rx';
import { GiCancel } from 'react-icons/gi';
import { IoIosArrowUp, IoIosArrowDown } from 'react-icons/io';

export default function ComparisonModal(
    {
        comparison,
        clearComparison,
        deleteSpecificComparison,
        handleComparisonView,
    }: {
        comparison: FoodListType,
        clearComparison: () => void,
        deleteSpecificComparison: (idx: number) => void
        handleComparisonView: () => void,
    }
) {
    const [isOpenModal, setIsOpenModal] = useState(true);
    
    const handleOpenModal = () => {
        setIsOpenModal(!isOpenModal);
    }

    const openComparisonView = () => {
        setIsOpenModal(false);
        handleComparisonView();
    }
    
    return (
        <div className='border-1 border-sub fixed bottom-0 flex-row items-center justify-center shadow-2xl rounded-xl bg-[white]'>
            {isOpenModal 
            ? 
                <div>
                    <div className='h-[10%] w-[20%] flex justify-center m-auto'>
                        <IoIosArrowDown onClick={handleOpenModal} size={24} className='rounded-none cursor-pointer text-info_s'/>
                    </div>
                    <div>
                    <div onClick={clearComparison} className='flex justify-end mx-10 mt-10 cursor-pointer'>
                        <RxReload size={16} className='mx-5 text-info_s'/>
                        <span className='font-semibold text-info_s text-12'>초기화</span>
                    </div>
                </div>
                {comparison 
                ? <div className='flex h-200 w-390'>
                    {comparison.map((comparisonItem, idx) => (
                        <div className='flex-row'>
                            <GiCancel onClick={() => deleteSpecificComparison(idx)} size={20} className='m-auto cursor-pointer text-end text-info_s'/>
                            <div key={comparisonItem.foodId} className='flex justify-center items-center w-[200px] flex-row border-r-1 border-info_s border-opacity-40 h-full'>{comparisonItem.foodName}</div>
                        </div>
                    ))}
                </div>
                : <div className='flex items-center justify-center h-200'>
                    <div className='flex flex-row items-center justify-center h-full w-200 border-r-1 border-info_s border-opacity-40'>비교1</div>
                    <div className='flex flex-row items-center justify-center h-full w-200'>비교2</div>
                </div>

                }
                <div onClick={openComparisonView} className=' cursor-pointer flex items-center justify-center rounded-[10px] bg-info_s w-350 h-40 text-[white] text-20 m-auto mt-20'>
                    <span>비교하기</span>
                </div>
            </div>
            :   <div className='flex justify-center m-auto transform shadow-lg ransition hover:-translate-y-1 motion-reduce:transition-none motion-reduce:hover:transform-none duration-2000 w-390'>
                    <IoIosArrowUp onClick={handleOpenModal} size={24} className='rounded-none cursor-pointer text-info_s'/>
                </div>
            }
        </div>
    );
}


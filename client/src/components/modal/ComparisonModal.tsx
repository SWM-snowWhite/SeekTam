import React, { useState } from 'react';
import { FoodListType, FoodType } from '../../pages/Main';
import { RxReload } from 'react-icons/rx';
import { GiCancel } from 'react-icons/gi';
import { IoIosArrowUp, IoIosArrowDown } from 'react-icons/io';
import ComparisonFood from '../ComparisonFood';

export default function ComparisonModal(
    {
        comparisonList,
        clearComparison,
        deleteSpecificComparison,
        handleComparisonView,
    }: {
        comparisonList: FoodListType,
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
        <div className='fixed bottom-0 flex-row items-center justify-center overflow-scroll bg-white rounded-md shadow-2xl cursor-pointer border-1 border-grey200'>
            {isOpenModal 
            ? 
                <div>
                    <div onClick={handleOpenModal} className='flex items-center justify-center h-40 m-auto rounded-md border-b-1 border-grey200'>
                        <span className='mx-10 font-bold text-grey600'>식품 비교 닫기</span>
                        <IoIosArrowDown size={24} className='rounded-none cursor-pointer text-info_s'/>
                    </div>
                    <div>
                    <div onClick={clearComparison} className='flex justify-end mx-10 mt-10 cursor-pointer'>
                        <RxReload size={16} className='mx-5 text-info_s'/>
                        <span className='font-semibold text-info_s text-12'>초기화</span>
                    </div>
                </div>
                {comparisonList 
                ? <div className='flex h-200 w-390'>
                    {comparisonList.map((comparisonItem: FoodType, idx) => (
                        <ComparisonFood
                            comparisonItem={comparisonItem}
                            deleteSpecificComparison={deleteSpecificComparison}
                        />
                    ))}
                </div>
                : <div className='flex items-center justify-center h-200'>
                    <div className='flex flex-row items-center justify-center h-full w-200 border-r-1 border-info_s border-opacity-40'>비교1</div>
                    <div className='flex flex-row items-center justify-center h-full w-200'>비교2</div>
                </div>

                }
                <div onClick={openComparisonView} className=' cursor-pointer flex items-center justify-center rounded-[10px] bg-p1000 w-350 h-40 text-white text-20 m-auto mt-20'>
                    <span>비교하기</span>
                </div>
            </div>
            :   <div 
                    className='flex items-center justify-center m-auto transform shadow-lg w-390 h-30'
                    onClick={handleOpenModal}
                >
                    <span className='mx-10 font-bold text-grey600'>식품 비교하기</span>
                    <IoIosArrowUp 
                        size={24} 
                        className='mx-10 rounded-none cursor-pointer text-info_s'
                    />
                </div>
            }
        </div>
    );
}


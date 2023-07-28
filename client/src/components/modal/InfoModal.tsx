import React, { useEffect } from 'react';
import IarcModal from './IarcModal';
import AllergyModal from './AllergyModal';
import EwgModal from './EwgModal';
import FodmapModal from './FodmapModal';
import { View } from '../../pages/FoodsDetail';

export default function InfoModal({
    handleModalClick,
    currentView
}: {
    handleModalClick: (view: View) => void, 
    currentView: View
}) {
    
    return (
        <div className=' top-0 left-0 z-500 h-[100%] w-[100%] fixed bg-[black] bg-opacity-50 flex justify-center items-center'>
            <div className='top-[50%] left-[50%] absolute translate-x-[-50%] translate-y-[-50%] p-10 border-3 border-main rounded-[10px] bg-[white] w-310 h-546 shadow-lg'>
                {currentView === "ewg" && <EwgModal/>}
                {currentView === "allergy" && <AllergyModal/>}
                {currentView === "cancer" && <IarcModal/>}
                {currentView === "fodmap" && <FodmapModal/>}
                <button onClick={()=>handleModalClick(null)} className='bottom-10 flex justify-center rounded-[10px] w-270 m-auto h-30 bg-main text-[white] text-20 font-bold'>
                    확인
                </button>
            </div>
        </div>
    );
}


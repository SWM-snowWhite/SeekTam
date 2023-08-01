import React from 'react';
import IarcModal from './IarcModal';

export default function InfoModal() {
    return (
        <div className='  h-full fixed bg-[black] w-390 bg-opacity-50 flex justify-center items-center'>
            <div className='border-3 border-main rounded-[10px] fixed bg-[white] w-310 h-546'>
                <IarcModal />
                <button className='bottom-10 fixed flex justify-center rounded-[10px] w-270 h-30 bg-main text-[white] text-20 font-bold'>
                    확인
                </button>
            </div>
        </div>
    );
}


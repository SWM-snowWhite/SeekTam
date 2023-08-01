import React from 'react';
import { FiArrowDownCircle } from 'react-icons/fi'
import { ViewOptions } from '../../pages/FoodsDetail';

export default function DetailViewOpenBtn({toggleViewOptions, view}: {toggleViewOptions: (viewOption: keyof ViewOptions) => void, view: keyof ViewOptions}): JSX.Element {

    return (
        <button onClick={() => toggleViewOptions(view)} className='flex items-center justify-center w-full m-auto rounded-lg shadow-sm bg-opacity-30 bg-info_s hover:bg-main hover:bg-opacity-30'>
            <span className='mx-5 text-12  text-opacity-60'>자세히 보기</span>
            <FiArrowDownCircle className=' text-opacity-60'/>
        </button>
    );
}


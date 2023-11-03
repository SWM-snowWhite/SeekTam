import React from 'react';
import { SearchOptionObjectType, SearchTitleType } from '../pages/Main';

export default function SearchOption(
    {
        optionKey,
        searchOption, 
        handleViewToggle,
        handleChangeGram,
        handleCondition
    }: {
        optionKey: SearchTitleType;
        searchOption: SearchOptionObjectType;
        handleViewToggle: (title: SearchTitleType) => void;
        handleChangeGram: (title: SearchTitleType, event: React.ChangeEvent<HTMLInputElement>) => void;
        handleCondition: (title: SearchTitleType) => void;
    }
    ): JSX.Element {

    return (
        <div className={`flex m-5 rounded-md text-12 p-3 text-[white] ${searchOption.view ? 'bg-info_s' : 'bg-info'}`}>
            <button 
                onClick={() => handleViewToggle(optionKey)} 
                className={`${searchOption.view ? 'bg-main' : 'bg-sub'} rounded-lg w-55 m-3`}>
                {searchOption.title}
                </button>
            <input 
                value={!searchOption.gram ? "" : searchOption.gram} 
                onChange={(e) => handleChangeGram(optionKey, e)} 
                disabled={searchOption.view ? false : true} 
                className='p-5 m-3 text-center rounded-lg bg-info text-info_s' 
                placeholder='함량을 입력해주세요'>
            </input>
            <button 
                onClick={() => handleCondition(optionKey)} 
                className={`${searchOption.view && searchOption.condition ? 'bg-main' : 'bg-sub'} rounded-lg p-7 m-5`}>
                이상
            </button>
            <button 
                onClick={() => handleCondition(optionKey)} 
                className={`${searchOption.view && !searchOption.condition ? 'bg-main' : 'bg-sub'} rounded-lg p-7 m-5`}>
                이하
            </button>
        </div>
    );
}


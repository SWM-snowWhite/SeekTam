import React, { useState } from 'react';
import SearchOption from './SearchOption';
import { SearchOptionObjectType, SearchOptionType, SearchTitleType } from '../pages/Keyword';

export default function SearchOptionBar(
    {
        searchOptions, 
        toggleOption,
        handleChangeGram,
        handleCondition
    }: {
        searchOptions: SearchOptionType;
        toggleOption: (title: SearchTitleType) => void;
        handleChangeGram: (title: SearchTitleType, event: React.ChangeEvent<HTMLInputElement>) => void;
        handleCondition: (title: SearchTitleType) => void;
    }
) {
 
    return (
        <div className='flex-row m-auto mt-10 border-dotted border-1 border-main w-320'>
            {/* <p className='text-center text-12 text-info_s'>검색 옵션</p> */}
            {
            Object.keys(searchOptions).map((key: string) => {
                return <
                    SearchOption 
                        key={key} 
                        optionKey={key as SearchTitleType}
                        searchOption={searchOptions[key as SearchTitleType]} 
                        toggleOption={toggleOption} 
                        handleChangeGram={handleChangeGram}
                        handleCondition={handleCondition}
                />
            })}
        </div>
    );
}


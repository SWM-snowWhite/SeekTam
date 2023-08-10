import React from 'react';
import SearchOption from './SearchOption';
import { SearchOptionType, SearchTitleType } from '../pages/Keyword';

export default function SearchOptionBar(
    {
        searchOptions, 
        handleViewToggle,
        handleChangeGram,
        handleCondition
    }: {
        searchOptions: SearchOptionType;
        handleViewToggle: (title: SearchTitleType) => void;
        handleChangeGram: (title: SearchTitleType, event: React.ChangeEvent<HTMLInputElement>) => void;
        handleCondition: (title: SearchTitleType) => void;
    }
) {
 
    return (
        <div className='flex-row m-auto mt-10 border-dotted border-1 border-main w-320'>
            {
            Object.keys(searchOptions).map((key: string) => {
                return <
                    SearchOption 
                        key={key} 
                        optionKey={key as SearchTitleType}
                        searchOption={searchOptions[key as SearchTitleType]} 
                        handleViewToggle={handleViewToggle} 
                        handleChangeGram={handleChangeGram}
                        handleCondition={handleCondition}
                />
            })}
        </div>
    );
}


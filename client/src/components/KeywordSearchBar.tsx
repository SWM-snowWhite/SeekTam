import React, { useEffect, useState } from 'react';
import { PiMagnifyingGlass } from 'react-icons/pi';
import { GiCancel } from 'react-icons/gi';

export default function KeywordSearchBar({
    fetchKeywordSearch, 
    keyword, 
    handleChangeKeyword,
    clearKeyword,
    handleKeyUp,
    fetchOptionKeywordSearch
}: {
    fetchKeywordSearch: (keyword: string) => void, 
    keyword: string,
    handleChangeKeyword: (event: React.ChangeEvent<HTMLInputElement>) => void,
    clearKeyword: () => void,
    handleKeyUp: (event: React.KeyboardEvent<HTMLInputElement>) => void,
    fetchOptionKeywordSearch: () => void
}) {
    useEffect(() => {
        fetchKeywordSearch(keyword)
    }, [keyword])

    return (
        <div className="flex m-auto mt-40 rounded-2xl border-1 border-main w-320 h-37">
            <input 
                className="text-main w-[90%] rounded-2xl focus:outline-none ml-10 placeholder:text-14 placeholder:text-sub " onChange={handleChangeKeyword} 
                onKeyUp={handleKeyUp} 
                placeholder='식품명 또는 원재료명을 입력 해 주세요' 
                value={keyword}
            />
                {keyword 
                ? <button onClick={clearKeyword}>
                    <GiCancel size={20} className='mr-5 text-[white] bg-sub rounded-2xl' />
                </button>
                : <></>
            }
            <button onClick={() => fetchOptionKeywordSearch()}>
                <PiMagnifyingGlass size={26} className='mr-10 text-main' />
            </button>
        </div>
    );
}


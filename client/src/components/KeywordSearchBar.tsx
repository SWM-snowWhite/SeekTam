import React, { useEffect, useState } from 'react';
import { PiMagnifyingGlass } from 'react-icons/pi';
import { GiCancel } from 'react-icons/gi';

export default function KeywordSearchBar({
    searchKeyword, 
    keyword, 
    changeKeyword,
    deleteKeyword
}: {
    searchKeyword: (keyword: string) => void, 
    keyword: string,
    changeKeyword: (event: React.ChangeEvent<HTMLInputElement>) => void,
    deleteKeyword: () => void
}) {
    useEffect(() => {
        searchKeyword(keyword)
    }, [keyword])

    const handleClick = () => {
        searchKeyword(keyword)
    }

    return (
        <div className="flex m-auto mt-40 rounded-2xl border-1 border-main w-320 h-37">
            <input className="text-main w-[90%] focus:outline-none ml-10 placeholder:text-14 placeholder:text-sub " onChange={changeKeyword} placeholder='식품명 또는 원재료명을 입력 해 주세요' value={keyword}/>
            {keyword 
                ? <button onClick={deleteKeyword}>
                    <GiCancel size={20} className='mr-5 text-[white] bg-sub rounded-2xl' />
                </button>
                : <></>
            }
            <button onClick={handleClick}>
                <PiMagnifyingGlass size={26} className='mr-10 text-main' />
            </button>
        </div>
    );
}


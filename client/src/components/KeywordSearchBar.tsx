import React, { useEffect, useState } from 'react';
import { PiMagnifyingGlass } from 'react-icons/pi';

export default function KeywordSearchBar({searchKeyword, relatedFoodList}: {searchKeyword: Function, relatedFoodList: Array<string>}) {
    const [keyword, setKeyword] = useState('');
    
    useEffect(() => {
        searchKeyword(keyword)
    }, [keyword])
    
    const handleKeywordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setKeyword(e.target.value)
    }

    

    const handleClick = () => {
        searchKeyword(keyword)
    }

    return (
        <div className="flex mt-40 rounded-2xl border-1 border-main w-320 h-37">
            <input className="text-main w-[90%] focus:outline-none m-5 placeholder:text-14 placeholder:text-sub " onChange={handleKeywordChange} placeholder='식품명 또는 원재료명을 입력 해 주세요' value={keyword}/>
            <button onClick={handleClick}>
                <PiMagnifyingGlass size={26} className='mr-10 text-main' />
            </button>
        </div>
    );
}


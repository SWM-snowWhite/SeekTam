import React, { useEffect, useLayoutEffect, useRef } from 'react';
import { Link } from 'react-router-dom';

type KeywordComponentProps = {
    relatedFoodList: string[],
    keyword: string,
    focusedFoodIdx: number
    search: (keyword?: string | undefined) => void
};


export default function KeywordComponent({
    relatedFoodList, keyword, search, focusedFoodIdx
}: KeywordComponentProps) {
    const divRef = useRef<HTMLDivElement>(null);

    useLayoutEffect(() => {
        console.log(`current, ${divRef}`)
        if (divRef.current !== null) divRef.current.focus()
    });
    return (
        <div className='h-auto m-auto rounded-xl w-320 border-1 border-main'>
            {
            relatedFoodList.map((food, index) => {
                const keywordIdx = food.indexOf(keyword)
                const keywordLength = keyword.length
                const prefix = food.slice(0, keywordIdx)
                const postfix = food.slice(keywordIdx + keywordLength)
                    
                if (keywordIdx === -1) {
                    return (
                            <div className='cursor-grab' onClick={() => search(food)} key={index}>
                                <button >
                                    <span className='ml-5 text-main'>{food}</span>
                                </button>
                            </div>
                    )
                } else {
                    return (
                            <div ref={divRef} onClick={() => search(food)} key={index} className='flex h-30 rounded-xl cursor-pointer hover:bg-[#f9e3e3]'>
                                <button>
                                        {
                                            prefix !== "" ? <span className="ml-5 text-[black]">{prefix}</span> : <></>
                                        }
                                            <span className='ml-5 text-main'>{keyword}</span>
                                        {
                                            postfix !== "" ? <span className="text-[black]">{postfix}</span>: <></>
                                        }
                                </button>
                            </div>
                    )
                }
            })}
        </div>
    );
}


import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

type KeywordComponentProps = {
    relatedFoodList: string[],
    keyword: string
};


export default function KeywordComponent({
    relatedFoodList, keyword
}: KeywordComponentProps) {
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
                        <Link to={`/foods/detail/123`} key={index}>
                            <div key={index}>
                                <button>
                                    <span className='ml-5 text-main'>{food}</span>
                                </button>
                            </div>
                        </Link>
                    )
                } else {
                    return (
                        <Link to={`/foods/detail/123`} key={index}>
                            <div key={index} className='flex h-30 rounded-xl  hover:bg-[#f9e3e3]'>
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
                        </Link>
                    )
                }
            })}
        </div>
    );
}


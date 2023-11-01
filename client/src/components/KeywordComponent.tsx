import React from 'react';

type KeywordComponentProps = {
    relatedFoodList: string[],
    keyword: string,
    focusedFoodIdx: number
    fetchOptionKeywordSearch: (keyword?: string | undefined) => void
};

export default function KeywordComponent({
    relatedFoodList, keyword, fetchOptionKeywordSearch, focusedFoodIdx
}: KeywordComponentProps) {
    return (
        <div className='h-auto m-auto rounded-xl w-450 border-1 border-main'>
            {
            relatedFoodList && relatedFoodList.map((foodName: string, index: number) => {
                if (foodName.length > 22) {
                    foodName = foodName.slice(0, 22) + "..."
                }
                const keywordIdx = foodName.indexOf(keyword)
                const keywordLength = keyword.length
                const prefix = foodName.slice(0, keywordIdx)
                const postfix = foodName.slice(keywordIdx + keywordLength)
                    
                if (keywordIdx === -1) {
                    return 
                } else {
                    return (
                            <div onClick={() => fetchOptionKeywordSearch(foodName)} key={index} className={`flex ${index === focusedFoodIdx ? 'bg-p1000 bg-opacity-20 ' : ''}h-30 rounded-md cursor-pointer hover:bg-p200`}>
                                <button>
                                        {
                                            prefix !== "" ? <span className="ml-5 text-[black]">{prefix}</span> : <></>
                                        }
                                            <span className='ml-5 font-bold text-p1000'>{keyword}</span>
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


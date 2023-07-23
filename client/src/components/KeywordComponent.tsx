import React from 'react';

export default function KeywordComponent({relatedFoodList}: {relatedFoodList: Array<string>}) {
    return (
        <div className='h-auto w-320 border-1 border-main rounded-2xl'>
            {relatedFoodList.map((food, index) => (
                    <div key={index}>
                        <button>
                            <span className='ml-5 text-main'>{food}</span>
                        </button>
                    </div>
            ))}
        </div>
    );
}


import React from 'react';
import KeywordSearchBar from '../components/KeywordSearchBar';
import KeywordSearchPageBar from '../components/KeywordSearchPageBar';

export default function Search() {
    return (
        <div className='absolute flex-row h-full bg-white w-500'>
            <KeywordSearchPageBar/>
            {/* {!optionView ? (
				<div
					onClick={handleOptionViewClick}
					className='flex items-center justify-center p-5 mt-10 rounded-lg h-30 ml-35 w-320 border-1 border-info text-12'
				>
					<LuArrowDownWideNarrow className='w-12 h-12 mx-5' />
					<span className='text-14'>검색 옵션</span>
				</div>
			) : (
				<div
					onClick={handleOptionViewClick}
					className='flex items-center justify-center p-5 mt-10 rounded-lg h-30 ml-35 w-320 border-1 border-info text-12'
				>
					<LuArrowUpNarrowWide className='w-12 h-12 mx-5' />
					<span className='text-14'>닫기</span>
				</div>
			)}

			{optionView ? (
				<SearchOptionBar
					searchOptions={searchOptions}
					handleViewToggle={handleViewToggle}
					handleChangeGram={handleChangeGram}
					handleCondition={handleCondition}
				/>
			) : (
				<></>
			)}
			<FoodList
				foodList={foodList}
				selectedKeyword={selectedKeyword}
				isSearched={isSearched}
				handleSelectedFood={handleSelectedFood}
				addComparison={addComparison}
			/> */}
        </div>
    );
}


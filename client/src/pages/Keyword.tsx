import React, { useEffect, useState } from 'react'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios';
import KeywordComponent from '../components/KeywordComponent';
import NavigatorExceptSearch from '../components/NavigatorExceptSearch';
import SearchOptionBar from '../components/SearchOptionBar';
import {LuArrowDownWideNarrow, LuArrowUpNarrowWide} from 'react-icons/lu';
import FoodList from '../components/FoodList';

export type SearchTitleType = "kcal" | "carb" | "prot" | "fat";
export type SearchOptionObjectType = {
    title: string,
    gram: number,
    condition: number,
    view: number,
}

export type SearchOptionType = {
    [key in SearchTitleType]: SearchOptionObjectType
}

export type FoodType = {
    foodId: number,
    foodName: string,
    manufacture: string,
}

export type FoodListType = Array<FoodType> | []

const searchOptionList: SearchOptionType = {
    "kcal": {
        title: "칼로리",
        gram: 0,
        condition: 1,
        view: 0,
    },
    "carb": {
        title: "탄수화물",
        gram: 0,
        condition: 1,
        view: 0,
    },
    "prot": {
        title: "단백질",
        gram: 0,
        condition: 1,
        view: 0,
    },
    "fat": {
        title: "지방",
        gram: 0,
        condition: 1,
        view: 0,
    },
};



export default function Keyword() {
	const [relatedFoodList, setRelatedFoodList] = useState([]);
	const [searchOptions, setSearchOptions] = useState(searchOptionList);
	const [keyword, setKeyword] = useState<string>('');
    const [optionView, setOptionView] = useState<Boolean>(false)
    const [foodList, setFoodList] = useState<FoodListType>([])
    const [selectedKeyword, setSelectedKeyword] = useState("")
    const [focusedFoodIdx, setFocusedFoodIdx] = useState<number>(-1)
    const [isSearched, setIsSearched] = useState(false);
    const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL;

    // 키워드가 초기화 될 경우 관련 검색어 초기화
	useEffect(() => {
		if (keyword === "") {
			setRelatedFoodList([])
            setFocusedFoodIdx(-1)
		}
	}, [keyword])

    // 푸드 리스트가 변경 시 관련 검색어, 키워드 초기화
    useEffect(() => {
			setRelatedFoodList([])
            setKeyword("")
	}, [foodList])

	const changeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setKeyword(e.target.value)
    }

	const deleteKeyword = () => {
        setKeyword("")
    }

	const searchKeyword = (keyword: string) => {
		if (keyword === "") return
		
        axios.get(`${SERVER_API_URL}/foods/search/syllable?keyword=${keyword}`)
        .then((res) => {
            setRelatedFoodList(res.data)
        })
        .catch((err) => {
            alert("검색에 실패하였습니다.")
            console.log("검색에 실패하였습니다.", err);
        })
    }

	const handleKeyUp = (e: React.KeyboardEvent<HTMLInputElement>) => {
		if (e.key === 'Enter') {
			if (relatedFoodList.length === 0) return
            if (focusedFoodIdx === -1) {
                search();
                return
            }
            search(relatedFoodList[focusedFoodIdx]) ;
            return
		}

        if (e.key === 'ArrowUp') {
            if (relatedFoodList.length === 0) return 
            if (focusedFoodIdx > -1 && focusedFoodIdx < relatedFoodList.length) setFocusedFoodIdx(focusedFoodIdx - 1)
        }
    if (e.key === 'ArrowDown') {
        if (relatedFoodList.length === 0) return 
        if (focusedFoodIdx < relatedFoodList.length - 1) setFocusedFoodIdx(focusedFoodIdx + 1)
            
        }
	}

	const toggleOption = (title: SearchTitleType) => {
        const newView = searchOptions[title].view === 0 ? 1 : 0;
        let newSearchOptions;

        // 뷰가 꺼지면 gram을 0으로 되돌림
        if (newView === 0) {
            newSearchOptions = {...searchOptions, [title]: {...searchOptions[title], view: newView, gram: 0}};
        } else {
            newSearchOptions = {...searchOptions, [title]: {...searchOptions[title], view: newView}};
        }
        
        setSearchOptions(newSearchOptions);
    }

    // const handleSelectedFood = (foodName: string) => {
    //     setSelectedKeyword(foodName)
    // }
    
    const changeGram = (title: SearchTitleType, gram: number) => {
        const newSearchOptions = {...searchOptions, [title]: {...searchOptions[title], gram}};
        setSearchOptions(newSearchOptions);
    }
    
    const handleChangeGram = (title: SearchTitleType, e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.value === "") {
            changeGram(title, 0)
            return
        }

        if (typeof(Number(e.target.value)) !== "number") {
            alert("숫자만 입력 가능합니다.")
            return
        }

        if (e.target.value.length > 3) {
            alert("숫자는 세 자리까지만 입력 가능합니다.")
            return
        }
        changeGram(title, Number(e.target.value))
    }

    const handleCondition = (title: SearchTitleType) => {
        const newCondition = searchOptions[title].condition === 0 ? 1 : 0;
        const newSearchOptions = {...searchOptions, [title]: {...searchOptions[title], condition: newCondition}};
        setSearchOptions(newSearchOptions);
    }

    const search = async (clicked?: string) => {
        if (!clicked && keyword === "") return

        const url = await getUrl(clicked)
        const foodName = clicked ? clicked : keyword

        setSelectedKeyword(foodName)
        console.log(`url: ${url}`)
        try {
            const response = await axios.get(url)
            setFoodList(response.data)
            setIsSearched(true)
        } catch(e) {
            // alert("검색 결과가 없습니다.")
            console.log("검색 결과가 없습니다.", e)
        }
    }

    const getUrl = async (clicked?: string) => {
        
        let url = `${SERVER_API_URL}/foods/search?keyword=${clicked ? clicked : keyword}&`;

        await Object.keys(searchOptions).forEach((key: string) => {
            const option = searchOptions[key as SearchTitleType];
            if (option.view === 1) {
                url += `${key}=${option.gram}&`;
                url += `${key}_con=${option.condition}&`;
            }
        })
        return url.slice(0, -1);
    }
    
	
    const handleOptionViewClick = () => {
        setOptionView(!optionView)
    }
    
	return (
		<div className='flex-row align-center w-390 border-1 border-main'>
			<NavigatorExceptSearch/>
			<KeywordSearchBar search={search} searchKeyword={searchKeyword} keyword={keyword} changeKeyword={changeKeyword} deleteKeyword={deleteKeyword} handleKeyUp={handleKeyUp}/>
			{relatedFoodList.length > 0 
            ? <KeywordComponent relatedFoodList={relatedFoodList} keyword={keyword} search={search} focusedFoodIdx={focusedFoodIdx}/> 
            : <></>}
            {
                !optionView 
                ? <div onClick={handleOptionViewClick} className='flex items-center justify-center p-5 mt-10 rounded-lg h-30 ml-35 w-320 border-1 border-info text-12'>
                    <LuArrowDownWideNarrow className='w-12 h-12 mx-5'/>
                    <span className='text-14'>검색 옵션</span>
                </div>
                :
                <div onClick={handleOptionViewClick} className='flex items-center justify-center p-5 mt-10 rounded-lg h-30 ml-35 w-320 border-1 border-info text-12'>
                    <LuArrowUpNarrowWide className='w-12 h-12 mx-5'/>
                    <span className='text-14'>닫기</span>
                </div>
            }
            
            {
                optionView 
                ? <SearchOptionBar
                    searchOptions={searchOptions} 
                    toggleOption={toggleOption}
                    handleChangeGram={handleChangeGram}
                    handleCondition={handleCondition}
                />
                : <></>
            }
            <FoodList foodList={foodList} selectedKeyword={selectedKeyword} isSearched={isSearched}/>
		</div>
	)
}

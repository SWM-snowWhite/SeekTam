import React, { useEffect, useState } from 'react'
import { Route, useNavigate } from 'react-router-dom'
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
	const navigate = useNavigate();

	useEffect(() => {
		// 키워드가 초기화 될 경우 푸드 리스트도 초기화
		if (keyword === "") {
			setRelatedFoodList([])
		}
	}, [keyword])
	const changeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setKeyword(e.target.value)
    }

	const deleteKeyword = () => {
        setKeyword("")
    }

	const searchKeyword = (keyword: string) => {
		if (keyword === "") return
		
        axios.get(`http://localhost:3003/api/foods/search/${keyword}`)
        .then((res) => {
			// 테스트 코드 시작
			const randomNumber = Math.round((Math.random()) * 10)
			const newSetList = res.data.related_food_list.filter((food: string, idx: number) => idx < randomNumber)
            setRelatedFoodList(newSetList)
			// 테스트 코드 종료

			// 실제 코드
			// setRelatedFoodList(res.data.related_food_list) 

        })
        .catch((err) => {
            console.log('err: ', err);
        })
    }

	const handleKeyUp = (e: React.KeyboardEvent<HTMLInputElement>) => {
		if (e.key === 'Enter') {
			if (relatedFoodList.length === 0) {
				return
			}
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

    
    
    const changeGram = (title: SearchTitleType, gram: number) => {
        const newSearchOptions = {...searchOptions, [title]: {...searchOptions[title], gram}};
        setSearchOptions(newSearchOptions);
    }
    
    const handleChangeGram = (title: SearchTitleType, e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.value === "") {
            changeGram(title, 0)
            return
        }
        console.log(typeof(Number(e.target.value)))
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

        // console.log(`urL: ${url}, foodName: ${foodName}`)
        try {
            const response = await axios.get(url)
            setFoodList(response.data.data)
        } catch(e) {
            alert("검색 결과가 없습니다.")
            console.log(e)
        }
    }

    const getUrl = async (clicked?: string) => {
        
        let url = "http://localhost:3003/api/foods/search?";
        
        if (clicked) {
            url += `keyword=${clicked}&`;
        } else if (keyword !== "") {
            console.log(`keyword ${keyword}`)
            url += `keyword=${keyword}&`;
        }

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
            ? <KeywordComponent relatedFoodList={relatedFoodList} keyword={keyword} search={search}/> 
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
            <FoodList foodList={foodList}/>
		</div>
	)
}

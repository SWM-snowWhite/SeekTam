import React, { useEffect, useState } from 'react'
import { Route, useNavigate } from 'react-router-dom'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios';
import KeywordComponent from '../components/KeywordComponent';
import NavigatorExceptSearch from '../components/NavigatorExceptSearch';

export default function Keyword() {
	const [relatedFoodList, setRelatedFoodList] = useState([]);
	const [keyword, setKeyword] = useState('');
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
		
        axios.get(`http://localhost:3003/foods/search/${keyword}`)
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
			goToFirstKeyword()
		}
	}

	const handleSearchClick = () => {
		if (relatedFoodList.length === 0) {
			return
		}
		goToFirstKeyword()
	}

	const goToFirstKeyword = () => {
        navigate(`/foods/detail/${relatedFoodList[0]}`)
    }
	
	return (
		<div className='flex-row align-center w-390 border-1 border-main'>
			<NavigatorExceptSearch/>
			<KeywordSearchBar searchKeyword={searchKeyword} keyword={keyword} changeKeyword={changeKeyword} deleteKeyword={deleteKeyword} handleKeyUp={handleKeyUp} handleSearchClick={handleSearchClick}/>
			{relatedFoodList.length > 0 
            ? <KeywordComponent relatedFoodList={relatedFoodList} keyword={keyword}/> 
            : <></>}
		</div>
	)
}

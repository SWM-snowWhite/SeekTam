import React, { useEffect, useState } from 'react'
import { Route } from 'react-router-dom'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios';
import KeywordComponent from '../components/KeywordComponent';

export default function Keyword() {
	const [relatedFoodList, setRelatedFoodList] = useState([]);
	const [keyword, setKeyword] = useState('');

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
	
	return (
		<div className='flex-row align-center w-390 border-1 border-main'>
			<h1 className='font-bold text-center text-24 text-main'>키워드 검색 페이지</h1>
			<KeywordSearchBar searchKeyword={searchKeyword} keyword={keyword} changeKeyword={changeKeyword} deleteKeyword={deleteKeyword}/>
			{relatedFoodList.length > 0 
            ? <KeywordComponent relatedFoodList={relatedFoodList} keyword={keyword}/> 
            : <></>}
		</div>
	)
}

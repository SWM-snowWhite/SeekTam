import React, { useEffect, useState } from 'react'
import { Route } from 'react-router-dom'
import KeywordSearchBar from '../components/KeywordSearchBar'
import axios from 'axios';
import KeywordComponent from '../components/KeywordComponent';

export default function Keyword() {
	const [relatedFoodList, setRelatedFoodList] = useState([]);

	useEffect(() => {
		console.log(`relatedFoodList: ${relatedFoodList}`)
	}, [relatedFoodList])
	const searchKeyword = (keyword: string) => {
        axios.get(`http://localhost:3003/foods/search/${keyword}`)
        .then((res) => {
			// 테스트 코드 시작
			const randomNumber = Math.round((Math.random()) * 10)
			console.log(`randomNumber: ${randomNumber}`)
			
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
		<div className='flex-col justify-center w-390 border-1 border-main'>
			<h1 className='font-bold text-center text-24 text-main'>키워드 검색</h1>
			<KeywordSearchBar searchKeyword={searchKeyword} relatedFoodList={relatedFoodList} />
			{relatedFoodList 
            ? <KeywordComponent relatedFoodList={relatedFoodList}/> 
            : <></>}
		</div>
	)
}

import axios from 'axios';
import React, { useEffect } from 'react';
import FoodInfoComponent from '../components/FoodInfoComponent';

export type FoodInfoType = {
    foodId: number,
    foodNm: number,
    enerc: number,
    prot: number,
    fatce: number,
    chocdf: number,
    foodSize: number,
    companyName: string,
    sugar: number,
}

export type View = "ewg" | "cancer" | "allergy" | "fodmap" | null;

export default function FoodsDetail(
    {
        selectedFoodIdx,
    }: {
        selectedFoodIdx: number,
    }) {
    const [foodInfo, setFoodInfo] = React.useState<FoodInfoType>({foodId: 0, foodNm: 0, enerc: 0, prot: 0, fatce: 0, chocdf: 0, foodSize: 0, companyName: "", sugar: 0});
    const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL;
    
    useEffect(() => {
        getFoodDetail()
    },[])
    
    const getFoodDetail = () => {
        axios
            .get(`${SERVER_API_URL}/foods/search/detail?foodId=${selectedFoodIdx}`, {
                withCredentials: true,
            })
            .then(response => {
                setFoodInfo(response.data)
            })
            .catch(err => console.log(`상품 상세 정보 받아오기가 실패하였습니다.`, err))
    }
    return (
        <div className='flex-row items-center justify-center m-auto border-main'>
            <div className="flex-row items-center justify-center m-auto">
                {foodInfo
                ? 
                <div>
                    <p className='mt-10 text-center text-[#767676] text-12 font-bold'>{foodInfo.companyName}</p>
                    <p className='w-auto m-5 font-semibold text-center text-15'>{foodInfo.foodNm}</p>
                </div>
                : <></>
            }
            </div>
            {
                foodInfo 
                ? <FoodInfoComponent foodInfo={foodInfo}/>
                : <></>
            }
        </div>
    );
}


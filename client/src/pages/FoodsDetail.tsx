import axios from 'axios';
import React, { useEffect } from 'react';
import FoodInfoComponent from '../components/FoodInfoComponent';

export type FoodInfoType = {
    food_id: number,
    food_name: number,
    enerc: number,
    prot: number,
    fatce: number,
    chocdf: number,
    food_size: number,
    company_name: string,
    nutConSrtrQua: number,
    sugar: number,
}

export type View = "ewg" | "cancer" | "allergy" | "fodmap" | null;

export default function FoodsDetail(
    {
        selectedFoodIdx,
    }: {
        selectedFoodIdx: number,
    }) {
    const [foodInfo, setFoodInfo] = React.useState<FoodInfoType>({food_id: 0, food_name: 0, enerc: 0, prot: 0, fatce: 0, chocdf: 0, food_size: 0, company_name: "", nutConSrtrQua: 0, sugar: 0});
    const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL;
    useEffect(() => {
        getFoodDetail()
    },[])
    
    const getFoodDetail = () => {
        axios.get(`${SERVER_API_URL}/foods/search/detail?foodId=${selectedFoodIdx}`)
            .then(response => {
                setFoodInfo(response.data[0])
            })
            .catch(err => console)
    }
    return (
        <div className='flex-row items-center justify-center m-auto border-main'>
            <div className="flex-row items-center justify-center m-auto">
                {foodInfo
                ? 
                <div>
                    <p className='mt-10 text-center text-[#767676] text-12 font-bold'>{foodInfo.company_name}</p>
                    <p className='w-auto m-5 font-semibold text-center text-15'>{foodInfo.food_name}</p>
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


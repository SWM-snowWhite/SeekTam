import axios from 'axios';
import React, { useEffect } from 'react';
import Navigator from '../components/Navigator';
import FoodInfoComponent from '../components/FoodInfoComponent';

export type FoodInfoType = {
    [key: string]: string
}
export type View = "ewg" | "cancer" | "allergy" | "fodmap" | null;

export default function FoodsDetail() {
    const [foodInfo, setFoodInfo] = React.useState<FoodInfoType>({});
  
    useEffect(() => {
        const url: string = window.location.href;
        const foodId: number = Number(url.split("/").pop());
        getFoodDetail(foodId)
    },[])
    
    const getFoodDetail = (foodId: number) => {
        axios.get(`http://localhost:8080/foods/search/detail?foodId=${foodId}`)
            .then(response => {
                setFoodInfo(response.data[0])
            })
            .catch(err => console)
    }
    return (
        <div className='flex-row items-center justify-center m-auto w-390 border-1 border-main'>
            {!currentView ? <></> : <InfoModal handleModalClick={handleModalClick} currentView={currentView}/>}   

            <Navigator />
            <div className="flex-row items-center justify-center m-auto w-250 ">
                {/* <img src="https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/62299451926905-56e4e59b-41cb-4c98-92b2-25c0ea876873.png" className='shadow-md w-150 h-150'></img> */}
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
                ? 
                <FoodInfoComponent foodInfo={foodInfo}/>
                : <></>
                }
        </div>
    );
}


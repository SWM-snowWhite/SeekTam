import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './Ranking.css'

type MallRankingProps = {
    ranking: number;
    foodKeyword: string;
}

export default function MallRanking() {
    const SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
    const [rankingData, setRankingData] = useState<MallRankingProps[]>();
    // const [currentRank, setCurrentRank] = useState(0);
    
    useEffect(() => {
        getMallRanking();
    }, [])

    
    
    const getMallRanking = () => {
        axios
            .get(`${SERVER_API_URL}/mall/ranking`, {
                withCredentials: true,
            })
            .then(res => {
                const data: MallRankingProps[] = res.data;
                setRankingData(data);
                console.log(`data: ${JSON.stringify(data)}`);
            })
    };

    return (
        <div className='justify-center p-0 m-auto rounded-md shadow-md align-center w-320 mt-30 border-1 border-info'>
            <span className='flex justify-center'>실시간 트렌드 식품 검색어 👑</span>
            <ul className='flex flex-col justify-center bg-black ranking w-320'>
                {rankingData 
                ? 
                    rankingData.map((item: MallRankingProps, idx: number) => (
                        <li key={idx} className='bg-[#F4F4F4]'>
                            <span className='text-[#0E6C57] ml-10'>{item.ranking}</span>
                            <span className='ml-20'>{item.foodKeyword}</span>
                        </li> 
                    )) 
                : <></>}
            </ul>
        </div>
    );
}


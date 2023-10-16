import axios from 'axios';
import React, { useEffect, useState } from 'react';

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
                // 랭킹 기준으로 내림차순 정렬
                const sortedData: MallRankingProps[] = res.data.sort((a: MallRankingProps, b: MallRankingProps) => a.ranking - b.ranking)
                setRankingData(sortedData);
            })
    };

    return (
        <div className='justify-center p-0 m-auto rounded-md shadow-md align-center w-320 mt-30 border-1 border-info'>
            <span className='flex justify-center font-bold text-20'>실시간 트렌드 식품 검색어 👑</span>
            <ul className='flex flex-col w-[80%] m-auto rounded-md'>
                {rankingData 
                ? 
                    rankingData.map((item: MallRankingProps, idx: number) => (
                        <li key={idx} className='bg-[#F4F4F4] text-16 rounded-sm h-30'>
                            <span className='text-[#0E6C57] ml-10 font-bold'>{item.ranking}</span>
                            <span className='ml-20'>{item.foodKeyword}</span>
                        </li> 
                    )) 
                : <></>}
            </ul>
        </div>
    );
}


import axios from 'axios';
import React, { useEffect, useState } from 'react';

type MallRankingProps = {
    ranking: number;
    foodKeyword: string;
}

export default function MallRanking() {
    const REACT_APP_SERVER_API_URL = process.env.REACT_APP_SERVER_API_URL
    const [rankingData, setRankingData] = useState<MallRankingProps[]>();

    useEffect(() => {
        getMallRanking();
    }, [])

    const getMallRanking = () => {
        axios
            .get(`${REACT_APP_SERVER_API_URL}/mall/ranking`, {
                withCredentials: true,
            })
            .then(res => {
                const data: MallRankingProps[] = res.data;
                setRankingData(data);
                console.log(`data: ${JSON.stringify(data)}`);
            })
    };

    return (
        <div className='flex justify-center flex-column'>
        <div className=''>
            <span>실시간 트렌드 식품 검색어 👑</span>
            <div>
                {rankingData 
                ? 
                    rankingData.map((item: MallRankingProps, idx: number) => (
                        <div className='bg-[#F4F4F4]' >
                            <span className='text-[#0E6C57] pr-10'>{item.ranking}</span>
                            <span className=''>{item.foodKeyword}</span>
                        </div>
                        
                    )) 
                : <></>}
            </div>
        </div>
        </div>
    );
}


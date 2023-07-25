import axios from 'axios';
import React, { useEffect } from 'react';
import DetailViewBtn from '../components/DetailViewBtn';
import EwgDetail from '../components/detail/EwgDetail';
import CancerDetail from '../components/detail/CancerDetail';
import AllergyDetail from '../components//detail/AllergyDetail';
import FodmapDetail from '../components/detail/FodmapDetail';

export interface ViewOptions {
    ewg: string,
    cancer: string,
    allergy: string,
    fodmap: string
}

export default function FoodsDetail() {
    const [viewOptions, setViewOptions] = React.useState({
        ewg: false,
        cancer: false,
        allergy: false,
        fodmap: false
    });

    useEffect(() => {
        console.log(`viewOptions: ${JSON.stringify(viewOptions)}`)
    }, [viewOptions])
    useEffect(() => {
        const url: string = window.location.href;
        const foodName: string | undefined = url.split("/").pop();
        const decodedFoodName: string | undefined = decodeURIComponent(foodName || "");

        getFoodDetail(decodedFoodName)
    },[])

    const toggleViewOptions = (viewOption: keyof ViewOptions) => {
        let toggle = viewOptions[viewOption]
        const newViewOptions = {...viewOptions, [viewOption]: !toggle}
        console.log(`toggle: ${toggle}, viewOption: ${viewOption}, newViewOptions: ${JSON.stringify(newViewOptions)}`)
        setViewOptions(newViewOptions)
    }
    
    const getFoodDetail = (foodName: string) => {
        axios.get(`http://localhost:3003/foods/detail/${foodName}`)
            .then(response => {
                console.log(response.data)
            })
            .catch(err => console)
    }
    return (
        <div className='flex-row items-center justify-center h-full m-auto w-390 border-1 border-main'>
            <div className="flex-row items-center justify-center m-auto w-150 ">
                <img src="/logo.png" className='shadow-md w-150 h-150'></img>
                <p className='mt-10 text-center text-[#767676] text-12 font-bold'>농심</p>
                <p className='m-5 font-semibold text-center text-15'>진라면</p>
            </div>
            <div className='flex-row items-center justify-center '>
                <h1 className='font-semibold ml-34 text-20'>성분 정보</h1>
                <div className='flex-row m-auto mb-10 w-300 bg-[#F0F0F0] rounded-[10px]'>
                    <p className='mt-5 ml-16 font-bold text-14'>EWG 등급</p>
                    <div className='m-auto mt-10 flex items-center justify-center w-260 bg-[white] rounded-s-md rounded-e-md'>
                        <div className='w-[10%] m-2 text-[white] text-center bg-good'>1</div>
                        <div className='w-[20%] m-2 text-[white] text-center bg-low_danger'>2</div>
                        <div className='w-[30%] m-2 text-[white] text-center bg-high_danger'>3</div>
                        <div className='w-[40%] m-2 text-[white] text-center bg-unknown'>4</div>
                    </div>
                    <div className='flex justify-around m-auto'>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-good w-11 h-11'></div>
                            <span className='font-semibold text-10'>안전</span>
                        </div>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-low_danger w-11 h-11'></div>
                            <span className='font-semibold text-10'>낮은 위험도</span>
                        </div>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-high_danger w-11 h-11'></div>
                            <span className='font-semibold text-10'>높은 위험도</span>
                        </div>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-unknown w-11 h-11'></div>
                            <span className='font-semibold text-10'>정보 없음</span>
                        </div>
                    </div>
                    {viewOptions.ewg ? <EwgDetail /> : <></>}
                    <DetailViewBtn toggleViewOptions={toggleViewOptions} view={"ewg"}/>
                </div>
                <div className='flex-row m-auto mb-10 w-300 bg-[#F0F0F0] rounded-[10px]'>
                    <p className='mt-5 ml-16 font-bold text-14'>발암물질</p>
                    <p className='mt-10 ml-16 text-12'>발암 물질이 포함되지 않았습니다.</p>
                    {viewOptions.cancer ? <CancerDetail /> : <></>}
                    <DetailViewBtn toggleViewOptions={toggleViewOptions} view={"cancer"}/>
                </div>
                <div className='flex-row m-auto mb-10 w-300 bg-[#F0F0F0] rounded-[10px]'>
                    <p className='mt-5 ml-16 font-bold text-14'>알레르기 유발 성분</p>
                    <p className='mt-10 ml-16 bg-[#F0F0F0] border-none text-12 focus:outline-none'>밀, 대두, 계란, 쇠고기, 돼지고기, 닭고기, 조개류(굴, 홍합 포함) 함유</p>
                    {viewOptions.allergy ? <AllergyDetail /> : <></>}
                    <DetailViewBtn toggleViewOptions={toggleViewOptions} view={"allergy"}/>
                </div>
                <div className='flex-row m-auto mb-10 w-300 bg-[#F0F0F0] rounded-[10px]'>
                    <p className='mt-5 ml-16 font-bold text-14'>포드맵 유의 성분</p>
                    <div>
                        <div className='flex justify-around m-auto mb-5 w-250'>
                            <div>
                                <span className='text-info_s text-12'>과당</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                            <div>
                                <span className='text-info_s text-12'>유당</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                            <div>
                                <span className='text-info_s text-12'>만니톨</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                            <div>
                                <span className='text-info_s text-12'>솔비톨</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                            <div>
                                <span className='text-info_s text-12'>GOS</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                            <div>
                                <span className='text-info_s text-12'>프락탄</span>
                                <div className='m-5 rounded-full bg-good w-18 h-18'></div>    
                            </div>
                        </div>
                    </div>
                    <div className='flex justify-around m-auto mb-5 w-250'>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-good w-11 h-11'></div>
                            <span className='font-semibold text-10'>성분 적음</span>
                        </div>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-low_danger w-11 h-11'></div>
                            <span className='font-semibold text-10'>성분 보통</span>
                        </div>
                        <div className='flex items-center justify-center'>
                            <div className='m-5 rounded-full bg-high_danger w-11 h-11'></div>
                            <span className='font-semibold text-10'>성분 많음</span>
                        </div>
                    </div>
                    <span className='font-bold text-10 text-[#767676] flex justify-center'>각 항목별 가장 많은 함유 등급이 표시 됩니다.</span>
                    {viewOptions.fodmap ? <FodmapDetail /> : <></>}
                    <DetailViewBtn toggleViewOptions={toggleViewOptions} view={"fodmap"} />
                </div>
                <div className='flex-row m-auto mb-10 w-300 bg-[#F0F0F0] rounded-[10px]'>
                    <p className='mt-5 ml-16 font-bold text-14'>원재료명</p>
                    <div>
                        <p className='m-10 font-medium text-12'>면, 밀가루, 감자전분, 변성전분, 글루텐, 정제소금, 혼합제제,알파폴리겔, 기타가공품, 비타민B2, 육수추출농축액, 이스트엑기스3, 마늘시즈닝, 유화유지, 팜유, 정제수, 분말스프, 정제소금, 백설탕, 포도당, 칠리혼합추출물, 차맛양지분말, 참맛사골양념분말,참맛볶음장분말, 비프베이스분말, 쇠고기육수분말, 육수맛조미분</p>
                    </div>
                </div>
            </div>
            <div className='flex-row justify-center items-center w-345 h-80 border-1 border-[#767676] border-opacity-10 m-auto'>
                <span className='text-[#767676] flex justify-center text-12'>식탐의 구매하기는 쿠팡파트너스와 제휴합니다.</span>
                <button className='flex justify-center items-center m-auto w-320 h-50 bg-main text-[white] text-20 font-bold'>구매하러가기</button>
            </div>
        </div>
    );
}


import React from 'react';

export default function EwgModal() {
    return (
        <div className='flex-row items-center justify-center m-auto'>
            <h1 className='m-auto font-bold text-center text-20 bg-[grey] bg-opacity-20 rounded-md mb-10'>EWG 등급이란?</h1>
            <p className='font-bold text-14'>
                EWG는 "Enviroment Working Group" 약자로 미국의 비영리 환경단체입니다. 
            </p>
            <br/>
            <p className='font-bold text-14'>
                EWG 등급은 이 단체에서 만든 미국의 환경 관련 활동그룹으로 사설 비영리 단체인 EWG에서 발표하는 등급입니다. 
                </p>
            <br/>
            <p className='font-bold text-14'>
                이 자료는 공신력 있는 단체, 기관 등에서 발표된 데이터를 바탕으로 위험도 등급을 1~10단계로 분류하고 있습니다.
            </p>
            <br/>
            <p className='font-bold text-14'>
                숫자가 낮을수록 안전한 성분에 속하고 이 중에서도 1~2등급을 EWG 그린 등급, 3~6등급을 옐로 등급, 7~10등급을 레드 등급으로 분류합니다.
            </p>
            <br/>
            <div className='flex-row items-center justify-center m-auto'>
            <img className="m-auto" src="/images/low hazard.png" alt="ewg 등급 이미지"/>
            <img className="m-auto" src="/images/moderate hazard.png" alt="ewg 등급 이미지"/>
            <img className="m-auto" src="/images/high hazard.png" alt="ewg 등급 이미지"/>
            </div>
            <br/>
            <a href="https://www.ewg.org/">
                <span className='underline text-info_s text-12'> ▶︎ EWG 사이트 바로가기</span>
            </a>
        </div>
    );
}


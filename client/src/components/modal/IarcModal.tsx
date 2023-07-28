import React from 'react';

export default function IarcModal() {
    return (
        <div className='flex-row items-center justify-center m-auto'>
            <h1 className='font-bold text-20'>IARC 발암물질이란?</h1>
            <p className='font-bold text-12'>세계보건기구 산하 국제암연구소(International Agency for Research on Cancer, 이하 IARC)에서 발표하는 발암물질 등급을 말합니다.</p>
            <p className='font-bold text-12'>크게 아래와 같이 3가지로 분류됩니다.</p>
            <p className='font-bold text-12'>1군은 확정적 발암물질입니다.</p>
            <img src="/images/1군.png" alt="1군 발암물질 이미지"/>
            <p className='font-bold text-12'>2군은 발암 추정(probable) 물질입니다.</p>
            <img src="/images/2군.png" alt="2군 발암물질 이미지"/>            
            <p className='font-bold text-12'>3군은 발암 가능(possible) 물질입니다.</p>
            <img src="/images/3군.png" alt="3군 발암물질 이미지"/>            
        </div>
    );
}


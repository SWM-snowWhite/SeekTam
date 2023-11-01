import React from 'react';

export default function NotFound() {
    const notFoundImage = '/images/Graphic/4x/empty-food@4x.png' 
    
    return (
        <div className='flex flex-col w-full bg-grey100'>
            <img src={notFoundImage} className='w-full' alt="식품이 없는 이미지"/>
            <span className='flex justify-center mt-50'>찾으시는 식품이 없습니다 😭</span>
            {/* // Todo: 식품 등록하기 이후 해당 식품을 등록하시겠어요? 라는 문구로 변경
            <span className='flex justify-center mt-50'>해당 식품을 등록하시겠어요?</span> */}
            {/* <Button></Button> */}
        </div>
    );
}


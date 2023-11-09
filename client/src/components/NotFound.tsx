import React from 'react'

export default function NotFound() {
	const notFoundImage = '/images/Graphic/2x/empty-food@2x.png'

	return (
		<div className='flex-col justify-end h-full m-auto rounded-md bg-g100 w-450 h-250'>
			<img
				src={notFoundImage}
				className='justify-center m-auto w-150'
				alt='식품이 없는 이미지'
			/>
			<div className='font-medium text-center mt-15 text-16'>
				찾으시는 식품이 없습니다 😭
			</div>
			{/* // Todo: 식품 등록하기 이후 해당 식품을 등록하시겠어요? 라는 문구로 변경
            <span className='flex justify-center mt-50'>해당 식품을 등록하시겠어요?</span> */}
			{/* <Button></Button> */}
		</div>
	)
}

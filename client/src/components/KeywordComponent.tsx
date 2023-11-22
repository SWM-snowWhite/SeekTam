import React from 'react'

type KeywordComponentProps = {
	relatedFoodList: string[]
	keyword: string
	focusedFoodIdx: number
	fetchOptionKeywordSearch: (keyword?: string | undefined) => void
}

export default function KeywordComponent({
	relatedFoodList,
	keyword,
	fetchOptionKeywordSearch,
	focusedFoodIdx,
}: KeywordComponentProps) {
	return (
		<div className='fixed bg-white rounded-md w-480 top-80 border-1 border-p900'>
			{relatedFoodList &&
				relatedFoodList.map((foodName: string, index: number) => {
					if (foodName.length > 22) {
						foodName = foodName.slice(0, 22) + '...'
					}
					const keywordIdx = foodName.indexOf(keyword)
					const keywordLength = keyword.length
					const prefix = foodName.slice(0, keywordIdx)
					const postfix = foodName.slice(keywordIdx + keywordLength)

					if (keywordIdx === -1) {
						return
					} else {
						return (
							<div
								onClick={() =>
									fetchOptionKeywordSearch(foodName)
								}
								key={index}
								className={`flex ${
									index === focusedFoodIdx ? 'bg-g100 ' : ''
								}h-30 rounded-md cursor-pointer hover:bg-g100`}
							>
								<button>
									{prefix !== '' ? (
										<span className='ml-5 text-[black]'>
											{prefix}
										</span>
									) : (
										<></>
									)}
									<span className='ml-5 font-bold text-p1000'>
										{keyword}
									</span>
									{postfix !== '' ? (
										<span className='text-[black]'>
											{postfix}
										</span>
									) : (
										<></>
									)}
								</button>
							</div>
						)
					}
				})}
		</div>
	)
}

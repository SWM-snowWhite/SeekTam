import { PayloadAction, createSlice } from '@reduxjs/toolkit'

export interface IComparisonFood {
	foodId: number
	companyName: string
	foodName: string
	imageUrl: string
	like: boolean
}
const initialState: IComparisonFood[] = []

export const comparisonSlice = createSlice({
	name: 'comparisonList',
	initialState,
	reducers: {
		addComparisonFood: (
			state: IComparisonFood[],
			action: PayloadAction<IComparisonFood>,
		) => {
			if (state.length >= 5) {
				alert('비교함에는 상품을 5개까지 추가 가능합니다.')
				return state
			}
			state.push(action.payload)
		},
		filteredComparisonFood: (
			state: IComparisonFood[],
			action: PayloadAction<IComparisonFood>,
		) => {
			return state.filter(item => item.foodId !== action.payload.foodId)
		},
		resetComparisonFood: () => initialState,
	},
})

export const {
	addComparisonFood,
	filteredComparisonFood,
	resetComparisonFood,
} = comparisonSlice.actions
export default comparisonSlice.reducer

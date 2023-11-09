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
		updateComparisonFood: (
			state: IComparisonFood[],
			action: PayloadAction<IComparisonFood[]>,
		) => {
			state = action.payload
		},
		resetComparisonFood: () => initialState,
	},
})

export const { updateComparisonFood, resetComparisonFood } =
	comparisonSlice.actions
export default comparisonSlice.reducer

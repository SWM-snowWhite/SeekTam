import { PayloadAction, createSlice } from '@reduxjs/toolkit'

export type CurrentPageType =
	| 'home'
	| 'wishlist'
	| 'profile'
	| 'privacy'
	| 'agreement'

const initialState: CurrentPageType = 'home'

export const currentPageSlice = createSlice({
	name: 'currentPage',
	initialState: initialState as CurrentPageType,
	reducers: {
		currentPageUpdate: (state, action: PayloadAction<CurrentPageType>) => {
			return action.payload
		},
		currentPageReset: () => initialState,
	},
})

export const { currentPageUpdate, currentPageReset } = currentPageSlice.actions
export default currentPageSlice.reducer

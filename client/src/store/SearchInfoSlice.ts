import { PayloadAction, createSlice } from '@reduxjs/toolkit'
import { SearchTitleTypeKor } from '../pages/Search'

export interface SearchCondition {
	name: SearchTitleTypeKor
	content: number
	contentUpDown: number // -1(default) / 0(이하) / 1(이상)
}

export interface SearchInfo {
	isSearchFirst: boolean
	isOptionOpened: boolean
	isOptionHandlerOpened: boolean
	isSearchOn: boolean
	searchConditions: SearchCondition[]
}
const initialState: SearchInfo = {
	isSearchFirst: false,
	isOptionOpened: false,
	isOptionHandlerOpened: false,
	isSearchOn: false,
	searchConditions: [
		{
			name: '기본',
			content: 1,
			contentUpDown: 1,
		},
	],
}

export const searchInfoSlice = createSlice({
	name: 'searchInfo',
	initialState: initialState as SearchInfo,
	reducers: {
		updateSearchFirst: (
			state: SearchInfo,
			action: PayloadAction<boolean>,
		) => {
			state.isSearchFirst = action.payload
		},
		updateSearchOptionOpenState: (
			state: SearchInfo,
			action: PayloadAction<boolean>,
		) => {
			state.isOptionOpened = action.payload
		},
		updateSearchOptionHandlerOpenState: (
			state: SearchInfo,
			action: PayloadAction<boolean>,
		) => {
			state.isOptionHandlerOpened = action.payload
		},
		updateSearchOnOff: (
			state: SearchInfo,
			action: PayloadAction<boolean>,
		) => {
			state.isSearchOn = action.payload
		},
		updateSearchCondition: (
			state: SearchInfo,
			action: PayloadAction<SearchCondition[]>,
		) => {
			state.searchConditions = action.payload
		},
		resetSearchInfo: () => initialState,
	},
})

export const {
	updateSearchFirst,
	updateSearchOptionOpenState,
	updateSearchOnOff,
	resetSearchInfo,
	updateSearchCondition,
	updateSearchOptionHandlerOpenState,
} = searchInfoSlice.actions
export default searchInfoSlice.reducer

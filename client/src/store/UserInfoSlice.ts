import { PayloadAction, createSlice } from '@reduxjs/toolkit'

export interface UserInfo {
	id: number
	nickname: string
	email: string
	profileImageUrl?: string
}
const initialState: UserInfo = {
	id: 0,
	nickname: '',
	email: '',
	profileImageUrl: '',
}

export const userInfoSlice = createSlice({
	name: 'userInfo',
	initialState,
	reducers: {
		userInfoUpdate: (state: UserInfo, action: PayloadAction<UserInfo>) => {
			const { id, nickname, email, profileImageUrl } = action.payload
			state.id = id
			state.nickname = nickname
			state.email = email
			state.profileImageUrl = profileImageUrl
		},
		userInfoReset: () => initialState,
	},
})

export const { userInfoUpdate, userInfoReset } = userInfoSlice.actions
export default userInfoSlice.reducer

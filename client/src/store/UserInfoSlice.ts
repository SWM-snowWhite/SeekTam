import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface UserInfo {
    id: number;
    nickname: string;
    email: string;
}
const initialState: UserInfo = {
    id: 0,
    nickname: "",
    email: "",
}

export const userInfoSlice = createSlice({
    name: "userInfo",
    initialState,
    reducers: {
        update: (state: UserInfo, action: PayloadAction<UserInfo>) => {
            const { id, nickname, email } = action.payload;
            state.id = id;
            state.nickname = nickname;
            state.email = email;
        },
        reset: () => initialState
    },
})

export const { update, reset }  = userInfoSlice.actions
export default userInfoSlice.reducer
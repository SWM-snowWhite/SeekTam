import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export type CurrentPageType = "home" | "wishlist" | "profile"

export interface CurrentPageObjectType {
    key: CurrentPageType;
}

const initialState: CurrentPageObjectType = {
    key : "home"
}

export const currentPageSlice = createSlice({
    name: 'currentPage',
    initialState: initialState as CurrentPageObjectType,
    reducers: {
        currentPageUpdate: (state, action: PayloadAction<CurrentPageType>) => {
            state.key = action.payload; 
        },
        currentPageReset: () => initialState
    }
})

export const { currentPageUpdate, currentPageReset }  = currentPageSlice.actions
export default currentPageSlice.reducer
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getCartItems, postChangeCart } from "../api/CartApi";

export const getCartItemAsync = createAsyncThunk("getCartItemAsync", () => {
  return getCartItems();
});

export const postChangeCartAsync = createAsyncThunk(
  "postCartItemAsync",
  (param) => {
    return postChangeCart(param);
  }
);

const initState = [];

const cartSlice = createSlice({
  name: "cartSlice",
  initialState: initState,
  extraReducers: (builder) => {
    builder
      .addCase(getCartItemAsync.fulfilled, (state, action) => {
        console.log("getCartItemsAsync fulfilled");
        return action.payload;
      })
      .addCase(postChangeCartAsync.fulfilled, (state, action) => {
        console.log("postCartItemAsync fulfilled");
        return action.payload;
      });
  },
});

export default cartSlice.reducer;

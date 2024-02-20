import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { getCartItems, postChangeCart } from "../api/cartApi";
import { useRecoilState } from "recoil";
import { cartState } from "../atoms/cartState";
import { useEffect } from "react";

const useCustomCart = () => {
  const [cartItems, setCartItems] = useRecoilState(cartState);
  const queryClient = useQueryClient();
  const changeMutation = useMutation((param) => postChangeCart(param), {
    onSuccess: (result) => {
      setCartItems(result);
    },
  });

  const query = useQuery(["cart"], getCartItems, { staleTime: 1000 * 60 * 60 });
  useEffect(() => {
    if (query.isSuccess) {
      queryClient.invalidateQueries("cart");
      setCartItems(query.data);
    }
  }, [query.isSuccess]);

  const changeCart = (param) => {
    changeMutation.mutate(param);
  };

  return { cartItems, changeCart };
};

export default useCustomCart;

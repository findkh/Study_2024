import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const productsRouter = () => {
  const Loading = <div>Loaing...</div>;
  const ProductsList = lazy(() => import("../pages/products/ListPage"));
  const ProductsAdd = lazy(() => import("../pages/products/AddPage"));

  return [
    {
      path: "list",
      element: (
        <Suspense failback={Loading}>
          <ProductsList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace to="/products/list" />,
    },
    {
      path: "add",
      element: (
        <Suspense fallback={Loading}>
          <ProductsAdd />
        </Suspense>
      ),
    },
  ];
};

export default productsRouter;

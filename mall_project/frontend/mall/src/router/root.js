import { Suspense, lazy } from "react";
import todoRouter from "./todoRouter";
import productsRouter from "./productRouter";
import memberRouter from "./memberRouter";

const { createBrowserRouter } = require("react-router-dom");

const Loading = <div>Loading...</div>;
const Main = lazy(() => import("../pages/MainPage"));
const About = lazy(() => import("../pages/AboutPage"));
const TodoIndex = lazy(() => import("../pages/todo/IndexPage"));
const ProductsIndex = lazy(() => import("../pages/products/IndexPage"));

//createBrowserRouter : 어떤 경로에 어떤 컴포넌트를 보여줄지 결정하는 역할
const root = createBrowserRouter([
  {
    path: "", //경로가 root(/)이거나 아무것도 없을 때 Main을 보여준다.
    element: (
      <Suspense fallback={Loading}>
        <Main />
      </Suspense>
    ), //코드분할 : Suspense와 lazy는 필요한 순간까지 컴포넌트를 메모리상으로 올리지 않도록 지연로딩을 위해 사용
  },
  {
    path: "about",
    element: (
      <Suspense fallback={Loading}>
        <About />
      </Suspense>
    ),
  },
  {
    path: "todo",
    element: (
      <Suspense fallback={Loading}>
        <TodoIndex />
      </Suspense>
    ),
    children: todoRouter(),
  },
  {
    path: "products",
    element: (
      <Suspense fallback={Loading}>
        <ProductsIndex />
      </Suspense>
    ),
    children: productsRouter(),
  },
  {
    path: "member",
    children: memberRouter(),
  },
]);

export default root;

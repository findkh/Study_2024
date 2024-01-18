//BasicLayout 컴포넌트는 화면 상단에 고옹적인 메뉴와 링크를 보여주고 아래쪽으로 각 페이지 컴포넌트를 출력하는 구조로 작성

import BasicMenu from "../components/menus/BasicMenu";

const BasicLayout = ({ children }) => {
  return (
    <>
      <BasicMenu></BasicMenu>

      <div className="bg-white my-5 w-full flex flex-col space-y-r md:flex-row md:space-x-4 md:space-y-0">
        <main className="bg-sky-300 md:w-4/5 lg:w-3/4 px-5 py-5">
          {children}
        </main>

        <aside className="bg-green-300 md:w-1/5 lg:w-1/4 px-5 flex py-5">
          <h1 className="text-2xl md:text-4xl">Sidebar</h1>
        </aside>
      </div>
    </>
  );
};

export default BasicLayout;

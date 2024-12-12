import { useState, useRef, useReducer, useCallback, createContext, useMemo } from "react";
import "./App.css";
import Editor from "./components/Editor";
import Header from "./components/Header";
import List from "./components/List";

const mockData = [
  {
    id: 0,
    isDone: false,
    content: "React 공부",
    date: new Date().getTime(),
  },
  {
    id: 1,
    isDone: false,
    content: "출근하기",
    date: new Date().getTime(),
  },
  {
    id: 2,
    isDone: false,
    content: "놀러 가기",
    date: new Date().getTime(),
  },
];

function reducer(state, action){
  switch(action.type){
    case "CREATE" : return [action.data, ...state];
    case 'UPDATE' : return state.map((item) => 
      item.id === action.targetId 
      ? {...item, isDone: !item.isDone}
      : item
    );
    case 'DELETE' : return state.filter((item) => item.id !== action.targetId);
    default:
      return state;
  }

}

// 컴포넌트 외부에 정의
export const TodoStateContext = createContext();
export const TodoDispatchContext = createContext();

function App() {
  const [todos, dispatch] = useReducer(reducer, mockData);
  const idRef = useRef(3);

  const onCreate = useCallback((content) => {
    dispatch({
      type: "CREATE",
      data: {
        id: idRef.current++,
        isDone: false,
        content: content,
        date: new Date().getTime(),
      }
    })
  }, []);

  const onUpdate = useCallback((targetId) => {
    dispatch({
      type: 'UPDATE',
      targetId: targetId
    })
  }, []);

  const onDelete = useCallback((targetId) => {
    dispatch({
      type: 'DELETE',
      targetId: targetId
    })
  }, []);

  const memoizedDispatch = useMemo(() => {return {onCreate, onUpdate, onDelete}}, []);

  return (
    <div className="App">
      <Header />
      <TodoStateContext.Provider value={todos}>
        <TodoDispatchContext.Provider value={memoizedDispatch}>
          <Editor />
          <List />
        </TodoDispatchContext.Provider>
      </TodoStateContext.Provider>
    </div>
  );
}

export default App;
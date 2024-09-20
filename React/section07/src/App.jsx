import './App.css'
import Controller from './components/Controller'
import Viewer from './components/Viewer'
import Even from './components/Even'
import { useState, useEffect, useRef } from 'react'

function App() {
  const [count, setCount] = useState(0);
  const [input, setInput] = useState("");
  
  const isMount = useRef(false);

  // 마운트 -> 최초 한번 실행
  useEffect(() => {
    console.log("mount");
  }, [])

  // 업데이트
  useEffect(() => {
    if(!isMount.current) {
      isMount.current = true;
      return;
    }
    console.log("update");
  })

  // 언마운트


  // 배열 값이 변경 되면 화살표 함수의 내용이 실행됨
  useEffect(() => {
    // console.log('변경')
  }, [count, input]); //의존성 배열(dependency array == deps)

  const onClickButton = (value) => {
    setCount(count + value)
  }

  return (
    <div className='App '>
      <h1>Counter</h1>
      <section>
        <input 
          value={input} 
          onChange={(e) => {
            setInput(e.target.value)
          }} />
      </section>
      <section>
        <Viewer count={count}/>
        {count % 2 === 0 ? <Even /> : null}
      </section>
      <section>
        <Controller onClickButton={onClickButton}/>
      </section>
    </div>
  )
}

export default App

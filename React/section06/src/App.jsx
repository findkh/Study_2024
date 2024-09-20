import './App.css'
import Controller from './components/Controller'
import Viewer from './components/Viewer'
import { useState } from 'react'

function App() {

  const [count, setCount] = useState(0);

  const onClickButton = (value) => {
    console.log('ghcnf')
    setCount(count + value)
  }

  return (
    <div className='App '>
      <h1>Counter</h1>
      <section>
        <Viewer count={count}/>
      </section>
      <section>
        <Controller onClickButton={onClickButton}/>
      </section>
    </div>
  )
}

export default App

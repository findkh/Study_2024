import './App.css'
import Header from './components/Header'
import Main from './components/Main'
import Footer from './components/Footer'
import Button from './components/Button'
import { useState } from 'react'
import Bulb from './components/Bulb'
import Counter from './components/Counter'
import Register from './components/Register'

// const buttonProps = {
//   text: "메일",
//   color: "red",
//   num: 3
// }





function App() {
  return (
    <>
      <Register />
      {/* <Bulb />
      <Counter /> */}
      {/* <Button {...buttonProps}/>
      <Button text={"카페"} color={"green"}/>
      <Button text={"블로그"}>
        <div>자식요소</div>
      </Button> */}
    </>
  )
}

export default App


import { useState } from "react";
import "./App.css";
import { OutputData } from "./DataTypes/OutputData";
import { InputData } from "./DataTypes/InputData";

function App() {
  const [count, setCount] = useState(0);
  const [outputData, setOutputData] = useState<OutputData | null>(null);
  const [inputData, setInputData] = useState<InputData | null>(null);

  function handlePlus(x: number) {
    setCount((prev) => prev + x);
  }

  function handleMinus(x: number) {
    setCount((prev) => prev - x);
  }

  async function handleOutputData() {
    try {
      const response = await fetch("/data/testOutput.json");
      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }

      const result: OutputData = await response.json();
      setOutputData(result);
      console.log(result);
    } catch (error) {
      console.error("읽기 실패", error);
    }
  }

  async function handleInputData() {
    try {
      const response = await fetch("/data/testInput.json");
      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }

      const result: InputData = await response.json();
      setInputData(result);
      console.log(result);
    } catch (error) {
      console.error("읽기 실패", error);
    }
  }

  return (
    <>
      <h2>Typescript</h2>
      <h3>{`숫자: ${count}`}</h3>
      <div className="card">
        <button onClick={() => handlePlus(1)}>+1</button>
        <button onClick={() => handleMinus(1)}>-1</button>
      </div>

      <div className="card">
        <button onClick={() => handleOutputData()}>Output 데이터</button>
      </div>
      <h3>{`${outputData?.msgKey ?? ""}, ${outputData?.contents?.output?.params?.PATNO ?? ""}`}</h3>

      <div className="card">
        <button onClick={() => handleInputData()}>Input 데이터</button>
      </div>
      <h3>{`${inputData?.msgKey ?? ""}, ${inputData?.contents?.input?.params?.PATNO ?? ""}`}</h3>
    </>
  );
}

export default App;

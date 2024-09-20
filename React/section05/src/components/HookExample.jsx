import { useState } from "react"
import useInput from "../hooks/useInput";

// Hook
// 1. 함수  컴포넌트, 커스텀 훅 내부에서만 호출 가능.
// 2. 조건부로 호출 될 수 없다.
// 3. 커스텀 훅 만들 수 있다.



const HookExam = () => {
    const [input, onChange] = useInput();

    return (
        <div>
            <input value={input} onChange={onChange} />
        </div>
    )
}

export default HookExam
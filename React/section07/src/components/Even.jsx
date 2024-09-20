import { useEffect } from "react";

const Even = () => {
    useEffect(() => {
        // 클린업, 정리 함수
        return () => {
            console.log("unmount");
            
        }
    }, [])
    return <div>짝수</div>
}

export default Even;
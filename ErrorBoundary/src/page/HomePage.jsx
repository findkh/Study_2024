import { useEffect } from "react";
import { useAuth } from "../context/AuthProvider";
import { getData } from "../api/fakeApi";
import { useError } from "../context/ErrorContext";

export default function HomePage() {
  const { handleError } = useError(); // useError로 handleError 가져오기

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getData();
        // console.log(data);
      } catch (error) {
        handleError(error.message); // 에러 발생 시 ErrorContext로 에러 전달
      } finally {
        // setIsLoading(false); // 로딩 완료
      }
    };

    fetchData();
  });

  return <div>홈페이지</div>;
}

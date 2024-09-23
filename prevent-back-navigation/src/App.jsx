import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useNavigate,
} from "react-router-dom";
import Home from "./Home.jsx";
import About from "./About.jsx";
import Contact from "./Contact.jsx";

function App() {
  const navigate = useNavigate();

  useEffect(() => {
    // 첫 페이지에서 뒤로 가기를 방지하기 위한 초기 상태 추가
    const initialUrl = window.location.href;
    window.history.replaceState(null, null, initialUrl);
    window.history.pushState(null, null, initialUrl);

    const handlePopState = (event) => {
      alert("뒤로가기를 사용할 수 없습니다.");
      // 강제로 현재 페이지로 유지
      window.history.pushState(null, null, window.location.href);
    };

    window.addEventListener("popstate", handlePopState);

    return () => {
      window.removeEventListener("popstate", handlePopState);
    };
  }, [navigate]);

  return (
    <div>
      <h1>뒤로가기 이벤트 막기 예제</h1>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </div>
  );
}

const AppWrapper = () => (
  <Router>
    <App />
  </Router>
);

export default AppWrapper;

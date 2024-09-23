import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div>
      <h2>홈 페이지</h2>
      <Link to="/about">소개 페이지로 이동</Link>
      <br />
      <Link to="/contact">연락처 페이지로 이동</Link>
    </div>
  );
};

export default Home;

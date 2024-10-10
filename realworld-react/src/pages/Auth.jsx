import { useState } from "react";
import { Link, useNavigate, useMatch } from "react-router-dom";
import axios from "axios";
import { useAuth } from "../context/AuthContext";

function Auth() {
  const navigate = useNavigate();
  const isRegister = useMatch("/register");
  const { login } = useAuth();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();
    setIsSubmitting(true);

    try {
      const userData = {
        email,
        password,
        ...(isRegister && { username }),
      };

      const { data } = await axios.post(`/users${isRegister ? "" : "/login"}`, { user: userData });

      login(data.user); // 로그인 시 사용자 정보 저장
      navigate("/"); // 로그인 후 메인 페이지로 이동
    } catch (error) {
      // error.response가 존재하는지 확인
      if (error.response) {
        const { status, data } = error.response;

        if (status === 422) {
          setErrors(data.errors); // 유효성 검사 오류 처리
        } else if (status === 401) {
          setErrors({ email: "Invalid credentials" }); // 자격 증명 오류 처리
        } else {
          setErrors({ general: "An unexpected error occurred." }); // 기타 오류 처리
        }
      } else {
        // response가 없을 때
        setErrors({ general: "Network error or server is down." });
      }
    } finally {
      setIsSubmitting(false); // 제출 완료
    }
  }

  return (
    <div className="auth-page">
      <div className="container page">
        <div className="row">
          <div className="col-md-6 offset-md-3 col-xs-12">
            <h1 className="text-xs-center">Sign {isRegister ? "up" : "in"}</h1>
            <p className="text-xs-center">
              <Link to={isRegister ? "/login" : "/register"}>{isRegister ? "Have" : "Need"} an account?</Link>
            </p>
            <form onSubmit={handleSubmit}>
              {isRegister && (
                <fieldset className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Your Name"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </fieldset>
              )}
              <fieldset className="form-group">
                <input
                  type="email"
                  className="form-control form-control-lg"
                  placeholder="Email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </fieldset>
              <fieldset className="form-group">
                <input
                  type="password"
                  className="form-control form-control-lg"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </fieldset>
              {errors && (
                <div className="error-messages">
                  {Object.entries(errors).map(([key, value]) => (
                    <p key={key} className="text-danger">
                      {value}
                    </p>
                  ))}
                </div>
              )}
              <button disabled={isSubmitting} type="submit" className="btn btn-lg btn-primary pull-xs-right">
                Sign {isRegister ? "up" : "in"}
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Auth;

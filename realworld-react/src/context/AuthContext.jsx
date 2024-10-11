import { createContext, useContext, useState } from "react";
import axios from "axios";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [authUser, setAuthUser] = useState(() => {
    const jwt = localStorage.getItem("jwtToken");
    if (!jwt) return {};
    return jwt ? JSON.parse(atob(jwt)) : null;
  });

  const login = (user) => {
    setAuthUser(user);
    localStorage.setItem("jwtToken", btoa(JSON.stringify(user)));
    axios.defaults.headers.Authorization = `Token ${user.token}`;
  };

  const logout = () => {
    setAuthUser(null);
    localStorage.removeItem("jwtToken");
    delete axios.defaults.headers.Authorization;
  };

  const isAuth = !!authUser;

  return <AuthContext.Provider value={{ authUser, isAuth, login, logout }}>{children}</AuthContext.Provider>;
};

const useAuth = () => {
  return useContext(AuthContext);
};

// eslint-disable-next-line react-refresh/only-export-components
export { AuthProvider, useAuth };

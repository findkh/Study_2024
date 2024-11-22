import { useState } from "react";
import "./App.css";
import { ErrorProvider } from "./context/ErrorContext";
import { Route, Routes } from "react-router-dom";
import AuthProvider from "./context/AuthProvider";
import ErrorPage from "./page/ErrorPage";
import HomePage from "./page/HomePage";

function App() {
  return (
    <ErrorProvider>
      <Routes>
        <Route
          path="/t/:callId"
          element={
            <AuthProvider>
              <HomePage />
            </AuthProvider>
          }
        ></Route>
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </ErrorProvider>
  );
}

export default App;

import { ErrorProvider } from "./context/ErrorContext";
import { Route, Routes } from "react-router-dom";
import AuthProvider from "./context/AuthProvider";
import ErrorPage from "./page/ErrorPage";
import HomePage from "./page/HomePage";
import { WebSocketProvider } from "./context/WebSocketProvider";

function App() {
  return (
    <ErrorProvider>
      <Routes>
        <Route
          path="/t/:callId"
          element={
            <AuthProvider>
              <WebSocketProvider>
                <HomePage />
              </WebSocketProvider>
            </AuthProvider>
          }
        />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </ErrorProvider>
  );
}

export default App;

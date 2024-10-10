import "./App.css";
import { Link, Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Auth from "./pages/Auth";
import { AuthProvider } from "./context/AuthContext";
import Home from "./pages/Home";
import Article from "./pages/Article";

function App() {
  return (
    <AuthProvider>
      <Router>
        <header>
          <Navbar />
        </header>
        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<Auth key="register" />} />
            <Route path="/login" element={<Auth key="login" />} />
            <Route path="/article/:slug" element={<Article />} />
          </Routes>
        </main>
        <footer>
          <div className="container">
            <Link to="/" className="logo-font">
              conduit
            </Link>
            <span className="attribution">
              An interactive learning project from <a href="https://thinkster.io">Thinkster</a>. Code &amp; design
              licensed under MIT.
            </span>
          </div>
        </footer>
      </Router>
    </AuthProvider>
  );
}

export default App;

import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import makeServer from "./server.js";

makeServer();
createRoot(document.getElementById("root")).render(<App />);

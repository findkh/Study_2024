import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, "../config", "");

  return defineConfig({
    base: "/", // VITE_PUBLIC_URL을 base로 설정
    plugins: [react()],
    define: {
      // __APP_ENV__: JSON.stringify(env),
      global: "globalThis",
    },
    server: {
      proxy: {
        "/auth": {
          target: `http://localhost:8006`,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/auth/, "/call/auth"),
        },
        "/api": {
          target: `http://localhost:8006`,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, "/fake/api"),
        },
        "/stomp": {
          target: `ws://localhost:8006`,
          ws: true,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/stomp/, "/connect"),
        },
      },
    },
  });
});

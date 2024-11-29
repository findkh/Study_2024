import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, "./config", "");

  return defineConfig({
    base: "/",
    plugins: [react()],
    define: {
      __APP_ENV__: JSON.stringify(env),
    },
    server: {
      proxy: {
        "/auth": {
          target: `http://${env.SERVER_URL}:${env.SERVER_PORT}`,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/auth/, "/api/auth"),
        },
      },
    },
  });
});

import js from "@eslint/js";
import globals from "globals";
import react from "eslint-plugin-react";
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";

export default [
  { ignores: ["dist"] },
  {
    files: ["**/*.{js,jsx}"],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
      parserOptions: {
        ecmaVersion: "latest",
        ecmaFeatures: { jsx: true },
        sourceType: "module",
      },
    },
    settings: { react: { version: "18.3" } },
    plugins: {
      react,
      "react-hooks": reactHooks,
      "react-refresh": reactRefresh,
    },
    rules: {
      // ESLint 기본 권장 규칙
      ...js.configs.recommended.rules,
      // React 권장 규칙
      ...react.configs.recommended.rules,
      // JSX 런타임 규칙
      ...react.configs["jsx-runtime"].rules,
      // React Hooks 권장 규칙
      ...reactHooks.configs.recommended.rules,
      "no-unused-vars": "off",
      "react/prop-types": 0,
      // _blank 타겟을 사용하는 링크에 대해 경고 비활성화
      "react/jsx-no-target-blank": "off",
      // React 컴포넌트의 export에 대해 경고, 상수 내보내기를 허용
      "react-refresh/only-export-components": ["warn", { allowConstantExport: true }],
      "react/no-unused-prop-types": ["off"],
    },
  },
];

# Step 1: Build the React app using Vite (stg mode)
FROM node:20.10-alpine AS build

WORKDIR /app

# 의존성 설치
COPY package.json package-lock.json ./
RUN npm install

# 프로젝트 복사 및 빌드 (stg 모드)
COPY . ./
RUN npm run buildStg  # Vite의 stg 빌드 스크립트 실행

# Step 2: Set up Nginx to serve the build files
FROM nginx:alpine

# Nginx 설정 파일 복사
COPY nginx.conf /etc/nginx/nginx.conf

# Vite 빌드 파일을 Nginx의 html 폴더로 복사
COPY --from=build /app/dist /usr/share/nginx/html

# Nginx 포트 노출
EXPOSE 8000

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]

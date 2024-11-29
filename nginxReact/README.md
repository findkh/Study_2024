# nginx + react -> 도커 이미지 생성 테스트

이미지 생성

```
build --no-cache -t nginx-react:1.0.0-dev .
```

nginx + react

```
docker run -d -p 8000:8000 --name nginx-react-container nginx-react:1.0.0-dev
```

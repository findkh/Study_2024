# fastcampus-community_feed_service

유연하고 안전한 소프트웨어를 만들기 교육을 위한 프로젝트 입니다. Java, Spring Boot, Mysql 을 활용 하여 기본적인 객체지향 설계, 아키텍처 설계, 유지 보수가 쉬운 단위 테스트와 인수 테스트를 실무에 제대로 적용 할 수 있도록 프로젝트를 구성했습니다. 쉽게 테스트를 진행하는 방법, 좋은 아키텍처에 대해 설명합니다.

Spring boot - 3.3.1  
JDK - 17  
Mysql - 8.0.11

# Overall Architecture

![아키텍처](https://github.com/KanghoLee82/ch1_community_feed_service/assets/170936629/72712b3c-919f-405a-b22b-62ef6582729f)


# Part 1

## ch 1 ~ 4
강의의 목적과 수강생 분들의 목적을 설정합니다. 강의를 통해 단순 타이핑 보다 코드 작성에 대한 이유에 대해서 생각하는 법을 연습합니다.   
기본적인 객체 지향 설계를 수강생 관리 프로그램 예제를 통해 학습하고 Community Feed Service 를 순수 자바로만 설계하고 만드는 실습을 진행합니다.  
클린아키텍처의 개념과 클린아키텍처를 실습합니다. Junit5 를 활용한 실무에서도 적용 가능한 단위테스트와 클린아키텍처 적용을 실습합니다. Copilot을 활용한 손 쉬운 테스트 작성을 경험합니다.

## ch 5 ~ 6
순수 자바 프로그램으로 만들어진 Community Feed Service 에 Spring Boot 와 JPA를 추가하고 Mysql 을 연동하여 기능을 구현합니다.  
실제 실무에서 사용되는 쿼리문과 쿼리 튜닝을 통해 대량 데이터 핸들링과 테이블 설계를 함께 실습 합니다.  
rest-assured 를 적용해 유지보수가 쉬운 인수 테스트도 작성합니다.

# Part 2
## ch 1 ~ 2
Community Feed Service 에서의 인증 기능을 추가 구현해 봅니다. 기존에 없던 회원가입, 로그인과 JWT 인증 방식, Oauth 방식을 학습합니다. 기본적인 회원 도메인 구현에 대한 실습을 진행합니다.

## ch 3 ~ 4
Community Feed Service 에 추가적인 기능들을 추가합니다. 데이터가 많은 환경에서의 어드민 기능을 위한 api 제작, 모바일 푸시 기능, 동시에 좋아요를 눌렀을 때에 처리 및 대량의 요청에서 실무에서 어떻게 해결하는지 실습을 진행합니다.

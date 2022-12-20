# 기부마켓


## 📘소개

아름다운 가게에 따르면 2017년 기준 기부 물품 중 67.6%가 재사용이 불가능해 폐기 처리되었다는 기사를 보았습니다. 올바른 기부 문화를 위해 기부 물품을 받는 기관도 원하는 물품을 직접 보고 선택할 수 있으면 좋겠다고 생각하였습니다. 기부자가 물품을 올리면 피 기부 기관이 원하는 물품을 원하는 양만큼 주문할 수 있게 제작하였습니다.


## ☝️개발 내용
MSA 설계를 위해 회사 별로 서버와 데이터베이스를 나누고, Donator-Market에서는 권한 별로 서버를 나누었습니다.D
#### Donator-Market Company
- Database 
    - MySQL, AWS RDS
- 비회원
    - 회원가입, 로그인, 장바구니, 상품 상세 페이지
- 기부자
    - 상품등록, 상품수정, 상품관리, 판매이력, 답변하기
- 피 기부 기관
    - 장바구니, 주문하기, 구매이력, 질문하기, 배송조회
- 관리자
    - 회원목록 조회

#### Logistic Company
- Database 
    - MongoDB
- 송장번호 생성, 배송 상태 업데이트



## 🪛기술 스택

- Backend
    - Java, Spring(boot, security), JPA
- Frontend
    - ReactJS
- DB
    - MySQL, MongoDB, Redis
- etc
    - AWS(rds, S2), Docker, Kubernetes, RabbitMQ
    - Github, IntelliJ, VSCode, Window, Ubuntu, MSA

## DB 구조
![donator-market-database](https://user-images.githubusercontent.com/76933597/208621135-f5c9d7f7-00d1-46ec-b02c-7bff81d42360.PNG)
![logistic-database](https://user-images.githubusercontent.com/76933597/208621155-b58437e1-bf4c-41eb-809f-ebccd24f075c.PNG)



## 동작방식
추후에 이미지 바꿀거임
### Docker
![image](https://user-images.githubusercontent.com/76933597/208622363-61b59415-1fd8-4593-bd18-76ce2a081be2.png)

### Kubernetes


## 주요 기능
1. 로그인

![image](https://user-images.githubusercontent.com/76933597/208626275-409aa8c7-cbbf-4bb6-91b4-d7e3ed2e0929.png)

2. 배송조회(rabbitmq, scheduler)

3. 상품업로드 aws s3
4. restapi 예시 하나
5. 질문답변
6. 도커설명
7. 쿠버네티스 설명
    

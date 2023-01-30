# 기부마켓



## ☝️개발 내용
MSA 설계를 위해 회사 별로 서버와 데이터베이스를 나누고, Donator-Market에서는 권한 별로 서버를 나누었습니다.
#### Donator-Market Company Role
- 비회원
    - 회원가입, 로그인, 장바구니, 상품 상세 페이지
- 기부자
    - 상품등록, 상품수정, 상품관리, 판매이력, 답변하기
- 피 기부 기관
    - 장바구니, 주문하기, 구매이력, 질문하기, 배송조회
- 관리자
    - 회원목록 조회

### Donator-Market Database
- MySQL, RDS

#### Logistic Company
- 송장번호 생성, 배송 상태 업데이트

### Logistic Company
- MongoDB


## 🪛기술 스택

- Backend
    - Java, Spring(boot, security), JPA
- Frontend
    - ReactJS
- DB
    - MySQL, MongoDB, Redis
- etc
    - AWS(rds, S3), Docker, Kubernetes, RabbitMQ, MSA
    - Github, IntelliJ, VSCode, Window, Ubuntu

## DB 구조
![donator-market-database](https://user-images.githubusercontent.com/76933597/208621135-f5c9d7f7-00d1-46ec-b02c-7bff81d42360.PNG)
![logistic-database](https://user-images.githubusercontent.com/76933597/208621155-b58437e1-bf4c-41eb-809f-ebccd24f075c.PNG)


## 주요 기능
### 로그인
1. [로그인] 버튼 클릭
2. email, password 입력 후 [Sign in] 버튼 클릭
3. 리액트에서 email, password를 받은 후 json으로 변환
4. json으로 변환한 데이터를 '비회원' 서버로 post 형식으로 request
5. '비회원'서버에서 받은 후 JWT 토큰 생성 후 return
6. 리액트에서 콜백함수로 token 받음
7. 리액트 LocalStorage에 token 등록

### 상품등록
1. [상품등록] 버튼 클릭
2. 상품상태, 상품명, 재고, 상품 상세 내용 작성
3. [파일 선택] 클릭 후 이미지 파일 선택
4. [Submit] 버튼 클릭
5. AWS S3에 이미지 등록
6. 상품상태, 상품명, 재고, 상품상세 내용을 json 직렬화
7. 6번 값을 Blob 형식으로 formData에 담음
8. 이미지를 formData에 담음
9. formData를 post 형식으로 '기부자' 서버에 request
10. '기부자' 서버에서 받은 값을 데이터베이스에 저장
11. 데이터베이스 저장에 실패하면 alert("상품 등록 중 에러가 발생하였습니다.")
12. 403 혹은 400번 에러가 나면 alert("상품 등록 권한이 없습니다.) 후 login창으로 이동

### 주문&운송번호 생성
1. 상품 선택 후 [주문하기] 버튼 클릭
2. '피 기부 기관' 서버에서 OrderDto를 받아 데이터베이스에 저장(주문)
3. '기부자' 서버에서 OffderDto를 받아 데이터베이스에 저장(판매)
4. '피 기부 기관' 서버에서 RabbitTemplate을 이용하여 orderId를 '택배사' 서버에 request
5. '택배사' 서버에서 RabbitListener로 orderId를 받음
6. '택배사' 서버에서 스케줄러를 이용해 매일 a시 마다 받은 orderId를 이용하여 송장번호 생성
7. '택배사' 서버에서 송장번호, 배송시간, 배송상태, orderId를 담은 logistics를 json으로 변환
8. json으로 변환한 데이터를 rabbitTemplate을 이용하여 '피 기부 기관'에 전송
9. b시간 c시간 d시간을 스케줄러를 사용하여 배송상태를 인수중, 배송준비중, 배송중, 배송완료를 추가

### 배송조회
1. 운송번호 입력 후 [배송조회] 버튼 클릭
2. 리액트에서 입력한 값을 받아 post 형식으로 '피 기부 기관' 서버에 전송
3. '피 기부 기관' 서버에서 값을 받은 후 rabbitTemplate을 통해 '택배사' 서버에 운송번호 전송
4. '택배사' 서버에서 값을 받은 후 해당 운송번호에 해당하는 데이터를 json으로 변환 후 return
5. '피 기부 기관' 서버에서 RestTemplate을 이용하여 get방식으로 데이터를 받아옴
6. 받아온 데이터를 return
7. 리액트에서 콜백함수를 통해 return  된 값을 받아와서 웹브라우저에 출력




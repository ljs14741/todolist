# 모아이스 과제전형 - todolist

## 1. 과제 접속 링크
http://binary96.store/login<br>

## 2. API 목록
### 회원 API

POST /api/users/register: 회원 가입<br>
POST /api/users/login: 로그인<br>
POST /api/users/delete: 회원 탈퇴<br>

### todoList API<br>

GET /todos: todoList 목록 조회<br>
POST /todos/add: todoList 추가<br>
POST /todos/{id}/todoList: todolist 상태 변경<br>
## 3. 코드 구조

![image](https://github.com/ljs14741/todolist/assets/39641715/a4de8ba9-a16d-44db-8156-3825585e8e18)


## 4. 서비스 흐름
1. 사용자는 회원 가입을 진행하여 서비스에 가입합니다.<br>
2. 로그인 후 todoList 화면으로 이동합니다.<br>
3. todoList 화면에서는 최근 todoList와 전체 todoList 목록을 조회할 수 있습니다.<br>
4. 사용자는 todoList를 추가하고, todoList의 상태를 변경할 수 있습니다.<br>

## 5. 추가 고려 사항
1. 보안: 비밀번호는 BCryptPasswordEncoder를 사용하여 암호화해서 저장하도록 하였습니다.<br>
2. 예외 처리: API 요청 중 발생하는 예외를 try-catch문으로 구현하였습니다.<br>
3. 최근list 조회: 최근 todoList는 생성일자를 기준으로 하였습니다.<br>
4. 서버구축: aws-ec2, aws-rds로 구축하였습니다.<br>

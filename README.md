# zobiac
점자리(동아리) 강의실 조사 시스템 및 일정 관리 서비스

## 프로젝트 소개
동아리 활동 및 운영에 필요한 업무를 효율적으로 처리하기 위해 개발한 웹 서비스입니다. 업무에 필요한 정보를 중앙화하여 일관된 입력과 체게적인 관리가 가능하도록 설계했습니다.

## 문제점 기획 배경
+ #### 강의실 조사 과정 및 문구 자동 생성의 어려움
  - ###### 구성원들이 자신의 문장으로 기록하여 정보를 취합하는데 어려움
  - ###### 스프레드시트는 입력 값의 범위를 제한하거나 데이터 가공이 불편함
+ #### 유지보수 불편
  - ###### 오래된 정보에 대하여 재확인 알림이 필요함
  - ###### 강의실 정보를 찾아 수정하거나 삽입, 삭제하는 과정이 불편함
+ #### 동아리 운영 작업의 시스템화 필요
  - ###### 점자 교육 시간표 관리, 동아리 평가 점수 계산기와 같이 임원진이 매번 처리해야하는 업무 존재
  - ###### 하나의 서비스에 통합하여 운영 효율 향상

## Tech Stack

#### Backend
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
#### Frontend
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
#### Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
#### API
![Naver Map API](https://img.shields.io/badge/Naver%20Map%20API-03C75A?style=for-the-badge&logo=naver&logoColor=white)

## 기능
+ - [x] 강의실 정보 생성 / 수정 / 삭제 / 복구
+ - [X] 문구 csv파일 다운로드 
+ - [x] 회원 인증 및 인가
+ - [ ] 교육 시간표 관리
+ - [ ] 오래된 강의실 정보 알림
+ - [ ] 교외 활동 게시판

[code](https://github.com/qaoechi/lectureRoom)

## 화면
<img width="1280" height="510" alt="Image" src="https://github.com/user-attachments/assets/b662b8ac-a903-41c6-a770-325a637624e5" />
<img width="1280" height="712" alt="Image" src="https://github.com/user-attachments/assets/3948d7d5-f1ba-468c-a7a8-f5ee2dd84c0b" />
<img width="1280" height="306" alt="Image" src="https://github.com/user-attachments/assets/9d212297-a2ab-435d-a8f1-5826d1bc6ada" />

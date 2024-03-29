# 목차

1. [웹 서비스 소개](#웹-서비스-소개)
2. [기술 스택](#기술-스택)
3. [주요 기능](#주요-기능)
4. [프로젝트 구성도](#프로젝트-구성도)
5. [파일구성도](#파일구성도)
6. [구현결과](#구현결과)
7. [개발 인원](#개발-인원)
8. [개발 기간](#개발-기간)

## 웹 서비스 소개

- Weasport Connect은 스프링 프레임워크를 사용하여 MVC 패턴을 구현한 스포츠 모임 매칭 서비스입니다. 

## 기술 스택

![개발도구](https://github.com/bsw0215/weasport/assets/144658036/44a619c7-36d3-4cbf-bafd-40ed47fd7d3c)

- Frontend: Html, CSS, Javascript, thymeleaf
- Backend: Spring Boot, Mysql, Spring Security, Java -version 17, Spring Data JPA, Gradle, Oauth2.0

## 주요 기능
  
### 회원가입 및 로그인

- Spring Security와 Javascript, thymeleaf를 사용하여 여러가지 권한 로직과 회원가입을 구현하였습니다. 
- Oauth 2.0을 사용하여 네이버, 구글, 카카오 소셜 로그인을 구현하였습니다.

자체 사이트 회원가입 시 다음과 같은 유효성 검사를 수행합니다.
- 모든 칸은 공백이 없어야 한다.
- 닉네임 글자 수는 4~12글자로 작성해야 한다.
- 아이디에는 영어 또는 숫자만 입력해야 한다.
- 아이디 글자 수를 4~12글자로 작성해야 한다.
- 비밀번호는 8글자 이상으로 작성해야 한다.
- 비밀번호는 영문, 숫자, 특수문자(@$!%*#?&)만 사용해야 한다.
- 아이디 중복 검사를 한다.

일반 로그인은 Spring Security를 이용한 Form Login을 사용하였습니다. 로그인 후에는 내 정보 -> 정보 수정에서 닉네임, 이메일, 장소를 변경할 수 있습니다.

### 게시판

- 게시판은 페이징 처리를 하여 한 페이지당 정해진 개수의 게시글들이 출력될 수 있게 하였고, 아래에 화살표와 최대 10개의 페이지 숫자들을 넣어서 페이지를 넘길 수 있도록 하였습니다.

- 게시판에 글쓰기는 로그인 한 사람만 가능하며 summernote 웹에디터를 사용하였습니다. 게시글을 삭제하거나 수정하는 일은 작성한 사람만 가능하도록 설계하였습니다.

### 모임

- 모임의 운영자는 이름, 스포츠, 날씨, 인원, 진행 기간, 위치 정보를 작성하여 모임을 등록합니다. 모임 생성 시 자바스크립트를 이용해 필수 입력 항목을 검사하고, 일부 항목에 대한 논리적인 검증도 수행합니다.

- 모임 상세 페이지에서 자신의 모임 정보를 수정하거나 삭제할 수 있습니다. 참여자의 참여 요청을 승인, 거절, 내보내기 등의 기능이 있습니다.

- 모임의 참여자는 메인 페이지에서 키워드를 검색하거나 원하는 조건으로 모임을 필터링하여 선택할 수 있습니다. 모임 목록은 페이징 처리가 되어 있으며 datatables 라이브러리를 사용하여 만들었습니다. 모임이 종료된 경우 해당 모임은 마감 상태가 되어 출력되지 않습니다.

- 모임 제목 클릭시 상세 페이지로 이동하며, 상세 페이지에서는 로그인을 했을 때 댓글 작성 및 삭제가 가능합니다. 상세페이지에서 사용자는 모임 신청이 가능하며, 신청할 시 참여자 목록에 승인 대기중 상태로 추가됩니다. 

### 대시보드

- 신청한 모임은 자신의 대시보드에서 신청 대기 모임을 확인할 수 있고, 모임을 클릭하면 신청한 모임 페이지로 이동합니다.

- 대시보드에는 진행 중인 모임 수와 예정된 모임의 수가 출력됩니다. 그리고 사용자가 주최한 모임에 신청한 모임 승인 대기자 수와 해당 모임의 링크가 제공이 됩니다. 

- 대시보드에 기상청 단기예보 날씨 API로 현재 날씨 정보가 출력되고, 기상 상태에 따라 사용자에게 야외활동하기 적합한지 출력됩니다. 또 상단 네비게이션 바에서도 현재 날씨를 확인할 수 있습니다.

- 사용자가 내 정보 -> 정보 수정에서 선택한 주소에 따라 해당 주소의 날씨를 요청합니다.

### 웹 디자인

- 부트스트랩을 활용하여 다양한 환경에서 사용자가 편리하게 서비스를 이용할 수 있도록 반응형 웹 디자인을 구현하였습니다.

### 데이터 처리

- Spring Data JPA를 사용하여 데이터 수정, 삭제, 조회, 저장 등의 API 요청을 수행합니다.

- JavaScript에서는 Ajax를 활용하여 서버와 클라이언트 간 데이터 교환 및 조작이 이루어집니다.

## 프로젝트 구성도

![구성도](https://github.com/bsw0215/weasport/assets/144658036/257f7a48-882c-4886-a122-7035c41f6a7e)

## 파일구성도

![파일구성도](https://github.com/bsw0215/weasport/assets/144658036/5aa6e80b-350e-431f-8a24-239e4658f6e0)

## 구현결과

- 메인 화면: 모임 목록이 출력됩니다. 제목을 클릭하면 모임 상세 페이지로 이동합니다.

![image01](https://github.com/bsw0215/weasport/assets/144658036/5d39546f-181c-4213-abf4-c5f22e2ccca8)

- 모임 상세 페이지: 모임의 세부 정보와 참여자 목록이 출력되고 로그인시 댓글 작성 및 삭제, 모임 신청을 할 수 있습니다. 우측 상단에 날씨와 프로필이 출력됩니다. 날씨의 아이콘은 날씨에 따라서 변합니다. ex)비가 오면 비내리는 아이콘이 출력됩니다.

![image02](https://github.com/bsw0215/weasport/assets/144658036/1447e5b5-a848-4c38-a19a-0fa116e96fe4)
![image03](https://github.com/bsw0215/weasport/assets/144658036/386e2caf-50d6-4ab9-8eac-679689bd7ed4)

- 모임 상세 페이지, 수정(운영자) : 모임 운영자는 참여자를 승인하거나 내보낼 수 있고 모임을 수정하고 삭제할 수 있습니다.

![image04](https://github.com/bsw0215/weasport/assets/144658036/5c145c71-df05-4bf7-8cac-6114543e8d37)
![image05](https://github.com/bsw0215/weasport/assets/144658036/54d1c289-6c4c-40d8-8870-5ba5c80fb611)

- 대시보드: 진행중인 모임 수, 내 모임 승인 대기자 수(드롭다운 클릭시 모임 목록), 신청 대기 모임 수(드롭다운 클릭시 모임 목록), 예정된 모임 수, 현재 장소의 날씨 정보, 기상 상태에 따른 주의를 출력합니다. 현재 날씨 정보의 우측 상단 아이콘을 누르면 장소와 날짜가 출력됩니다.

![image06](https://github.com/bsw0215/weasport/assets/144658036/3732b435-312c-41be-b849-d2c8d12e8442)
![image07](https://github.com/bsw0215/weasport/assets/144658036/baac79f8-f54e-40c5-8463-1af24c10e5f3)

- 게시판: 게시글 목록 출력, 페이징 처리를 구현하였습니다.

![image08](https://github.com/bsw0215/weasport/assets/144658036/780dd504-b9a3-4f96-890f-403a3491c9fb)

- 게시글 작성: summernote 라이브러리를 활용하였습니다. 제목을 입력해야 등록이 가능합니다.

![image09](https://github.com/bsw0215/weasport/assets/144658036/4b4fc025-161c-4172-a83f-2df045dbfc86)

- 게시글 출력: 게시글 수정, 삭제, 댓글 작성, 삭제를 할 수 있습니다.

![image10](https://github.com/bsw0215/weasport/assets/144658036/1fb601f8-c70e-4f24-b446-be92f13f2de7)

- 게시글 수정

![image11](https://github.com/bsw0215/weasport/assets/144658036/feae565f-ef9b-4f44-ba6c-aaf3cb7ba6e5)

- 내 정보 수정: 닉네임, 이메일, 주소를 수정할 수 있습니다. 여기서 수정한 주소에 따라 날씨 정보들이 출력됩니다.

![image12](https://github.com/bsw0215/weasport/assets/144658036/a05d098e-025a-49e5-acb7-d5241ccf75aa)
![image13](https://github.com/bsw0215/weasport/assets/144658036/05fe5e06-acd8-46b7-9894-cc43b8fd5a5e)

- 로그인 화면: 소셜 로그인, 일반 로그인을 할 수 있습니다. Weasport Connect를 누르면 메인 페이지로 로그인 없이 이동됩니다.

![image14](https://github.com/bsw0215/weasport/assets/144658036/89cdf003-73ea-4644-8267-839a9a591e53)

- 회원가입 화면: 유효성 검사를 하여 회원가입을 할 수 있습니다.

![image15](https://github.com/bsw0215/weasport/assets/144658036/b082654f-18e4-4f30-af9d-f945184c30c8)

## 개발 인원

### 1인

## 개발 기간

### 2023.11.06 ~ 2023.12.15

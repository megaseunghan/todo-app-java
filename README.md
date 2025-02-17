# todo-app-java

### 공통 조건

1. 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 `비밀번호`는 제외되어 있습니다.
2. 일정 수정, 삭제 시 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 가능합니다.

### 필수 구현 기능(요구사항)

- [ ]  생성 - 일정 작성
    - [ ]  `할 일 제목`, `할 일 내용`, `담당자`, `비밀번호`, `작성일` 을 저장할 수 있습니다.
        - [ ]  저장된 일정 정보를 반환 받아 확인할 수 있습니다.
- [ ]  조회 - 선택한 일정 조회(상세 페이지)
    - [ ]  선택한 일정의 정보를 조회할 수 있습니다.
    - [ ]  반환 받은 일정 정보에는 `할 일 제목`,`할 일 내용`, `작성일`, `작성자 이름` 정보가 들어있습니다.
- [ ]  조회 - 일정 목록 조회
    - [ ]  등록된 일정 전체를 조회할 수 있습니다.
    - [ ]  조회된 일정 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.
- [ ]  수정 - 선택한 일정 수정
    - [ ]  선택한 일정의 `할 일 제목`, `할 일 내용`, `담당자`를 수정할 수 있습니다.
    - [ ]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - [ ]  생성 시에, 입력한 비밀번호와 일치할 경우에만 수정할 수 있습니다.
    - [ ]  수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
- [ ]  삭제 - 선택한 일정 삭제
    - [ ]  서버에 일정 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
        - [ ]  생성 시에, 입력한 비밀번호와 일치할 경우에만 수정할 수 있습니다.
        

<aside>
🔥 **추가 구현 기능**

</aside>

### 선택 구현 기능(요구사항)

- [ ]  [`HTTP 상태 코드(링크)`](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)와 `에러 메시지`를 포함한 정보를 사용, 예외처리
    - 설명
        - 필요에 따라 사용자 정의 예외 클래스를 생성하여 예외 처리를 수행할 수 있습니다.
        - **`@ExceptionHandler`**를 활용하여 공통 예외 처리를 구현할 수도 있습니다.
        - 예외가 발생할 경우 적절한 HTTP 상태 코드와 함께 사용자에게 메시지를 전달하여 상황을 관리
    - 조건
        - [ ]  수정, 삭제 시 요청할 때 보내는 `비밀번호`가 일치하지 않을 때
        - [ ]  선택한 일정 정보가 이미 삭제되어 조회할 수 없을 때
        - [ ]  삭제하려는 일정 정보가 이미 삭제 상태일 때
- [ ]  `Swagger` 활용 / 파라미터 유효성 검사 / **`null 체크`** / **`특정 패턴`**에 대한 검증
    - 설명
        - Swagger
            - API 명세서를 직접 작성하는 대신 [**`Swagger(링크)`**](https://springdoc.org/#Introduction)를 활용하여 자동으로 생성할 수 있습니다.
            - 개발자는 코드와 함께 API 명세서를 업데이트하고 관리할 수 있어서 `개발 생산성`을 높일 수 있습니다.
            - Swagger UI를 통해 직관적인 인터페이스를 통해 API를 쉽게 이해하고 테스트할 수 있습니다.
        - 유효성 검사
            - 잘못된 입력이나 요청을 미리 방지할 수 있습니다.
            - 데이터의 `무결성을 보장`하고 애플리케이션의 예측 가능성을 높여줍니다.
            - Spring에서 제공하는 **`@Valid`** 어노테이션을 이용할 수 있습니다.
    - 조건
        - [ ]  Swagger
            - [ ]  Swagger UI를 통해 API 목록을 확인할 수 있다.
            - [ ]  Swagger UI를 통해 API 테스트를 할 수 있다.
        - [ ]  유효성 검사
            - [ ]  `할일 제목`은 최대 200자 이내로 제한, 필수값 처리
            - [ ]  `비밀번호`는 필수값 처리
            - [ ]  `담당자`는 이메일 형식을 갖도록 처리

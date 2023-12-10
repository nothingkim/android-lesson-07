## 모바일 프로그래밍 14주차 실습 프로젝트

 # 실행 방법
1. h2.bat 실행
   - JDBC URL : jdbc:h2:~/mem/testDB
   - 사용자명 : easw
   - 비밀번호 : temp_password
   - 'create table FILELIST' 쿼리 입력 후 실행
2. Spring boot Applicaiton 실행
   - Id : admin , PW : admin으로 로그인
   - s3 API key를 이용하여 로그인


---
<hr>
모바일 프로그래밍 14주차 실습 프로젝트입니다.

해당 실습 프로젝트는 예제 코드가 존재하지 않습니다.

이전 실습 프로젝트를 확인하여 온전한 프로젝트를 구성하세요.

다음 목표 중, 로그인과 회원가입 혹은 파일 업로드와 목록만을 구현해도 정답 처리됩니다.

다음은 구현 목표입니다. 다음 목표의 대분류중 하나를 완료하세요.

- [ ] Spring Security를 이용하여 로그인 및 회원가입을 구현해야 합니다.
  - [ ] Spring Security에 기본 계정 (admin / guest)를 추가해 로그인 기능을 구현하야 합니다.
  - [ ] Spring Security를 사용해 회원가입 기능을 구현해야 합니다. 
- [ ] 파일 목록 조회 및 업로드, 다운로드를 구현해야 합니다.
    - [ ] 파일 업로드와 다운로드는 S3을 통해서 진행되어야 합니다.
    - [ ] 파일 목록은 데이터베이스에 저장되어 조회되어야 합니다. (데이터베이스는 H2를 사용합니다.)
    - [ ] 어플리케이션 시작시, 이전의 버킷을 파기하고 다시 생성해야 합니다. 버킷은 내부의 모든 파일이 삭제되어야 삭제할 수 있습니다.

S3 관련 내용은 [android-lesson-01](https://github.com/java-lesson-cbnu/android-lesson-01) 레포지토리를, 스프링 시큐리티 관련 내용은 [android-lesson-04](https://github.com/java-lesson-cbnu/android-lesson-04) 레포지토리를, 회원가입 관련은 [android-lesson-06](https://github.com/java-lesson-cbnu/android-lesson-06) 레포지토리를 확인하세요.


과제 제출은 해당 레포지토리를 포크한 후, 레포지토리 링크를 [skywolf461@gmail.com](mailto://skywolf461@gmail.com)으로 학번, 이름과 함께 제출하세요.

포크 방법은 [해당 링크](https://ittrue.tistory.com/90)를, 커밋 방법은 [해당 링크](https://extbrain.tistory.com/155)를 참고하세요.

해당 프로젝트는 Java 17 이상에서만 작동합니다.

강의 시간 종료 후, 모르는 내용 혹은 질문이 있다면 [skywolf461@gmail.com](mailto://skywolf461@gmail.com)으로 메일을 전송하세요.


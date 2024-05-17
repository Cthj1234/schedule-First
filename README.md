# API 명세서
|기능|Method|URL|request|response|
|---|---|---|---|---|
|일정등록|Post|/create|{ "title":"제목", "contents":"내용", "manager":"담당자 이름", "password":"암호" }|{ "id":id, "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }|
|일정조회|Get|/find/{id}|id = {id}|{ "id":"id", "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }|
|전체조회|Get|/findAll|X|{"responseList" : { "id":id, "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }, ....}|
|일정수정|Post|/update/{id}|?id = {id}, { "title":"제목", "contents":"내용", "manager":"담당자 이름", "password":"암호"}|{id}|
|일정삭제|Delete|/delete/{id}|?id = {id} , String password|{id}|
</br>

---
# UseCase Diagram
<img src= "https://github.com/Cthj1234/schedule-First/assets/114575847/bc233c63-a83a-48af-b89c-4e925f0f2cff.jpg" width="500" height="500"/>


---
# Schedule - ERD

<img src= "https://github.com/Cthj1234/schedule-First/assets/114575847/51a92582-18bc-434d-9357-f077d51e5f4c.jpg" width="200" height="100"/>

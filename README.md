# API 명세서
|기능|Method|URL|request|response|
|---|---|---|---|---|
|일정 등록|Post|/create|{ "title":"제목", "contents":"내용", "manager":"담당자 이름", "password":"암호" }|{ "id":id, "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }|
|일정 조회|Get|/find/{id}|id = {id}|{ "id":"id", "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }|
|전체 조회|Get|/findAll|X|{"responseList" : { "id":id, "title":"제목", "contents":"내용", "manager" : "담당자 이름", "created_Time":"작성일" }, ....}|
|일정 수정|Post|/update/{id}|?id = {id}, { "title":"제목", "contents":"내용", "manager":"담당자 이름", "password":"암호"}|{id}|
|일정 삭제|Delete|/delete/{id}|?id = {id} , String password|{id}|
</br>

---
# UseCase Diagram
![image](https://github.com/Cthj1234/schedule-First/assets/114575847/7013cef2-2971-4561-a2a6-1aabb8442731)
<img src="(https://github.com/Cthj1234/schedule-First/assets/114575847/7013cef2-2971-4561-a2a6-1aabb8442731)." width="200" height="400"/>

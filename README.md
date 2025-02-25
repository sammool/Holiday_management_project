# Holiday_management_project
my first project using the Spring Framework
## 목적

군대에서 휴가를 관리하는 사람이 휴가를 일일이 수작업으로 관리하는 것을 보고 ‘이를 프로그램 화 해보면 업무에 도움이 되지 않을까?’생각이 들어 프로젝트를 진행하게 되었습니다.

## 데이터베이스 구조

![image.png](https://github.com/sammool/Holiday_management_project/blob/main/image.png?raw=true)

프로젝트에서는 다음과 같은 데이터베이스 구조를 구성하였습니다:

h2 database를 사용하였으며, JPA를 통해 객체들 간의 연관 관계를 설정하였습니다. 이 방식으로 멤버는 리더에게 휴가 정보를 전달할 수 있습니다.
<br>
## 개발 주요 사항

<img src="https://github.com/sammool/Holiday_management_project/blob/main/image%20(1).png?raw=true" width="300">

- **세션, 인터셉터**를 이용하여 회원은 회원은 관리자 페이지에 접근하지 못하도록 하였습니다.
<br>

### <회원 기능>

![image.png](https://github.com/sammool/Holiday_management_project/blob/main/image%20(2).png?raw=true)

- **@Validated** 를 이용한 검증을 통해 가능할 휴가 일 수, 올바른 날짜를 입력 받도록 하였습니다.
<br>

<img src="https://github.com/sammool/Holiday_management_project/blob/main/image%20(3).png?raw=true" width="300">

- 회원은 휴가를 취소할 수 있습니다.
<br>

### <관리자 기능>

![image.png](https://github.com/sammool/Holiday_management_project/blob/main/image%20(4).png?raw=true) ![image.png](https://github.com/sammool/Holiday_management_project/blob/main/image%20(5).png?raw=true)

- 관리자는 전체 회원의 리스트를 조회하고, 각 회원의 정보를 조회 및 수정할 수 있습니다.
<br>

![image.png](https://github.com/sammool/Holiday_management_project/blob/main/image%20(6).png?raw=true)

- 관리자는 전체 휴가 리스트를 조회하고, 각 휴가에 대하여 승인 또는 취소 할 수 있습니다.

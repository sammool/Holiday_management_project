# Holiday_management_project
my first project using the Spring Framework
## 목적

군대에서 휴가를 관리하는 사람이 휴가를 일일이 수작업으로 관리하는 것을 보고 ‘이를 프로그램 화 해보면 업무에 도움이 되지 않을까?’생각이 들어 프로젝트를 진행하게 되었습니다.

## 데이터베이스 구조

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/dd5e703d-fd2d-4cce-9cf3-fcc634083321/image.png)

프로젝트에서는 다음과 같은 데이터베이스 구조를 구성하였습니다:

h2 database를 사용하였으며, JPA를 통해 객체들 간의 연관 관계를 설정하였습니다. 이 방식으로 멤버는 리더에게 휴가 정보를 전달할 수 있습니다.

## 개발 주요 사항

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/3bf19517-0c06-4a65-a965-9662501ecb59/e21e93a1-23b4-41e8-b5a6-b74c6e613d15.png)

- **세션, 인터셉터**를 이용하여 회원은 회원은 관리자 페이지에 접근하지 못하도록 하였습니다.

### <회원 기능>

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/a1443468-0ce8-41f6-b6c9-c68bf8824aa0/image.png)

- **@Validated** 를 이용한 검증을 통해 가능할 휴가 일 수, 올바른 날짜를 입력 받도록 하였습니다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/40d311eb-4d30-44ce-93fe-492ac609f60b/2536a4b4-af66-4d26-937f-5c55e90cfa3b.png)

- 회원은 휴가를 취소할 수 있습니다.

### <관리자 기능>

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/0edae94e-71b2-4236-b234-8846aee3a991/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/587fd20e-ab45-4d6f-9b5b-32df0ad5f2cc/image.png)

- 관리자는 전체 회원의 리스트를 조회하고, 각 회원의 정보를 조회 및 수정할 수 있습니다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/06e57708-263e-4489-b447-49c9c4fcac79/47c781d9-31fc-47ab-972c-a3f80ad045a0/image.png)

- 관리자는 전체 휴가 리스트를 조회하고, 각 휴가에 대하여 승인 또는 취소 할 수 있습니다.

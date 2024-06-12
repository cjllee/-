주요 기술 스택
Java Development Kit (JDK): 11
Apache Tomcat: 9.0
Spring Framework: 5.3.20
MyBatis: 3.5.6
View : JSP , jstl(반복문 사용) , javascript ,ajax (비동기처리) 
H2 Database: 2.1.214
SLF4J: 1.7.30

```
create table board_table(
    id bigint primary key auto_increment,
    boardWriter varchar(50),
    boardPass varchar(20),
    boardTitle varchar(50),
    boardContents varchar(500),
    boardCreatedTime datetime default now(),
    boardHits int default 0,
    fileAttached int default 0
);

create table comment_table(
    id bigint primary key auto_increment,
    commentWriter varchar(50),
    commentContents varchar(200),
    boardId bigint,
    commentCreatedTime datetime default now(),
    constraint fk_comment_table foreign key (boardId) references board_table(id) on delete cascade
);

create table member_table(
	id bigint primary key auto_increment,
    memberEmail varchar(20) unique,
    memberPassword varchar(20),
    memberName varchar(20),
    memberAge int,
    memberMobile varchar(30)
); 
```

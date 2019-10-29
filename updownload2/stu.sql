create table student(
  id int primary key auto_increment,
  name varchar(10) not null,
  age int,
  score double(5,1),
  realName varchar(30),
  photoName varchar(50),
  photoType varchar(50)
);
select * from student
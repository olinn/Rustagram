CREATE TABLE ru_users
(
 id int Identity (1, 1) primary key NOT NULL,
 username nvarchar(256) unique NOT NULL,
 password nvarchar(256),
 displayName nvarchar(256),
 email nvarchar(256),
 gender nvarchar(32),
 registered datetime
)

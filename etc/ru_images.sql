CREATE TABLE ru_images
(
  id int IDENTITY (1, 1) PRIMARY KEY NOT NULL,
  creator_username nvarchar(256) FOREIGN KEY REFERENCES ru_users(username),
  created datetime,
  url nvarchar(2048),
  description ntext
)

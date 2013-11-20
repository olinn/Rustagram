CREATE TABLE ru_comments
(
  id int IDENTITY (1, 1) PRIMARY KEY NOT NULL,
  creator_username nvarchar(256) FOREIGN KEY REFERENCES ru_users(username),
  created datetime,
  image_id int FOREIGN KEY REFERENCES ru_images(id),
  comment ntext
)

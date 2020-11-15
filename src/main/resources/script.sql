CREATE TABLE ToDoList.Users (
	Id INT auto_increment NOT NULL,
	email varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	password varchar(500) NOT NULL,
	CONSTRAINT Users_PK PRIMARY KEY (email),
	CONSTRAINT Users_UN UNIQUE KEY (Id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

----------------------------------------


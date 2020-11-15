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

-- ToDoList.UserTasks definition

CREATE TABLE `UserTasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `task` varchar(5000) NOT NULL,
  `createdOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `dueDate` datetime DEFAULT NULL,
  `completed` enum('1','0') NOT NULL,
  `updatedOn` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('active','completed','archived','deleted') NOT NULL,
  UNIQUE KEY `UserTasks_UN` (`id`),
  KEY `UserTasks_FK` (`email`),
  CONSTRAINT `UserTasks_FK` FOREIGN KEY (`email`) REFERENCES `Users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
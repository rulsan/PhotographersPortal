CREATE TABLE IF NOT EXISTS `users` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR (40) NOT NULL,
`password` VARCHAR(32) NOT NULL,
`login` VARCHAR (40) UNIQUE NOT NULL,
`role` VARCHAR (20) NOT NULL,
`registration_date` DATETIME,
`last_activity_date` DATETIME,
`visible_email` TINYINT (1) DEFAULT 0,
`avatar_id` VARCHAR (50),
`camera` TEXT,
`contacts` TEXT
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `photo` (
`photo_id` VARCHAR (50) PRIMARY KEY,
`user_id` INT,
`tags` VARCHAR (255),
`description` TEXT,
`upload_date` DATETIME
)DEFAULT CHARSET=utf8;

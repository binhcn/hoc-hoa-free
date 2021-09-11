Link postman
https://www.getpostman.com/collections/cef9fc0cd67f1159ecef

CREATE TABLE hoc_hoa_free.`category` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `topic` (
     `id` int NOT NULL AUTO_INCREMENT,
     `title` varchar(45) NOT NULL,
     `category_id` int NOT NULL,
     PRIMARY KEY (`id`),
     KEY `fk_category_id_idx` (`category_id`),
     CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `exercise` (
    `id` int NOT NULL AUTO_INCREMENT,
    `question` varchar(200) NOT NULL,
    `topic_id` int NOT NULL,
    `solution_image` varchar(45) NOT NULL,
    `created_at` varchar(45) NOT NULL,
    PRIMARY KEY (`id`,`topic_id`),
    KEY `fk_topic_id_idx` (`topic_id`),
    KEY `created_at_idx` (`created_at` DESC),
    CONSTRAINT `fk_topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `hoc_hoa_free`.`category`(`id`, `title`) VALUES ('1', 'LỚP 11');
INSERT INTO `hoc_hoa_free`.`category`(`id`, `title`) VALUES ('2', 'LỚP 12');
INSERT INTO `hoc_hoa_free`.`category`(`id`, `title`) VALUES ('3', 'ĐỀ THI THỬ THPT');

INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('1', 'Điện ly', '1');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('2', 'Nito', '1');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('3', 'Este', '2');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('4', 'Peptit', '2');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('5', 'Kim loại', '2');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('6', 'Hidrocacbon thơm', '1');
INSERT INTO `hoc_hoa_free`.`topic` (`id`, `title`, `category_id`) VALUES ('8', 'Năm 2020', '3');

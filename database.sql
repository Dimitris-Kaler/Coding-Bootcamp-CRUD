DROP DATABASE IF EXISTS cube_coding_bootcamp1;

CREATE DATABASE IF NOT EXISTS cube_coding_bootcamp1;

USE cube_coding_bootcamp1;

CREATE TABLE courses (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`title` VARCHAR(25) NOT NULL,
`stream` VARCHAR(25) NOT NULL,
`type` VARCHAR(25) NOT NULL,
`start_date` DATE NOT NULL DEFAULT "1900-01-01",
`end_date` DATE NOT NULL DEFAULT "1900-01-01"
);

CREATE TABLE students (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) NOT NULL DEFAULT "",
`Last_name` VARCHAR(45) NOT NULL DEFAULT "",
`date_of_birth` DATE NOT NULL DEFAULT "1900-01-01",
`tuition_fees` FLOAT NOT NULL DEFAULT 2500
);

CREATE TABLE trainers(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) NOT NULL DEFAULT "",
`last_name` VARCHAR(45) NOT NULL DEFAULT "",
`subject` VARCHAR(25) NOT NULL 
);

CREATE TABLE assignments (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`title` VARCHAR(45) NOT NULL,
`description`VARCHAR(200) DEFAULT "",
`max_oral_mark` INT NOT NULL DEFAULT 20,
`max_total_mark` INT NOT NULL DEFAULT 100,
`sub_date_time` DATE NOT NULL DEFAULT "1900-01-01"
);

CREATE TABLE courses_per_students(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`courses_id` INT NOT NULL,
`students_id` INT NOT NULL,
CONSTRAINT courses_per_students_courses_id__courses_id FOREIGN KEY (`courses_id`) REFERENCES `courses`(`id`),
CONSTRAINT courses_per_students_students_id__students_id FOREIGN KEY (`students_id`) REFERENCES `students`(`id`)
);

CREATE TABLE courses_per_trainers (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`courses_id` INT NOT NULL,
`trainers_id` INT NOT NULL,
CONSTRAINT courses_per_trainers_courses_id__courses_id FOREIGN KEY (`courses_id`) REFERENCES `courses`(`id`),
CONSTRAINT courses_per_trainers_trainers_id__trainers_id FOREIGN KEY (`trainers_id`) REFERENCES `trainers`(`id`)
);
CREATE TABLE students_per_assignments(
`id`INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`oral_mark` INT NOT NULL DEFAULT "0",
`total_mark` INT NOT NULL DEFAULT "0",
`student_id` INT NOT NULL,
`assignment_id` INT NOT NULL,
`course_id` INT NOT NULL,
CONSTRAINT `students_per_assignments_student_id__students_id` FOREIGN KEY (`student_id`) REFERENCES `students`(`id`),
CONSTRAINT `students_per_assignments_assignment_id__assignments_id` FOREIGN KEY(`assignment_id`) REFERENCES `assignments`(`id`),
CONSTRAINT `students_per_assignments_course_id__courses_id` FOREIGN KEY(`course_id`) REFERENCES `courses`(`id`)
);

INSERT INTO students(first_name,last_name,date_of_birth,tuition_fees)
VALUES ("John","Tolkien","1987-2-1",2500),("Howard","Lovecraft ","1990-2-4",2200),("Leo","Tolstoy","1980-10-25",2500),("William","Shakespeare","1986-3-24",2200),
("Jules","Verne","1993-3-1",2500),("Charles","Dickens","1989-2-19",2000),("Vladimir","Nabokov","1988-1-29",2200),("Ernest","Hemingway","1991-10-21",2500),("Franz", "Kafka","1994-2-3",2200),("Jane","Austen","1990-5-20",2500),
("William", "Faulkner","1989-10-10",2000),("Anne","Frank","1991-6-6","2100"),("Oscar","Wilde","1993-2-23",2500),("Agatha","Christie","1990-12-10","2000"),
("Mark","Twain","1982-4-1","2500"),("Virginia","Woolf","1987-5-17","2500"),("George","Orwell","1977-3-3","2200"),("Isaac","Asimov","1987-5-4","2000"),("George","Martin","1988-8-8","2500"),
("Stephanie","Meyer","1979-2-12","2200"),("Nora","Roberts","1996-4-1","2200"),("Dale","Carnegie","1985-12-20","2500");


INSERT INTO trainers(first_name,last_name,subject)
VALUES ("Ludwig","Beethoven","Advanced CSS"),("Frédéric","Chopin","OOP"),("Richard","Wagner","Js React"),
("Johannes","Brahms","Functional-programming"),("Claude","Debussy","jquery"),("Antonio","Vivaldi","HTML/CSS"),("Barbara","Strozzi","Database design"),
("Isabella","Leonarda","Web Development"),("Wolfgang","Mozzart","Debugging");

INSERT INTO courses(title,stream,type,start_date,end_date)
VALUES ("CB10","Javascript","Part-Time","2021-2-1","2021-9-10"),("CB11","Java","Part-Time","2021-2-1","2021-9-10"),("CB12","Python","Part-time","2021-2-1","2021-9-10"),
("CB14","C#","Full-Time","2021-10-1","2021-4-15"),("CB15","C#","Part-Time","2021-2-1","2021-9-10"),("CB16","Javascript","Full-Time","2021-10-1","2021-4-15"),
("CB17","Python","Full-Time","2021-10-1","2021-4-15"),("CB18","Java","Full-Time","2021-10-1","2021-4-15");

INSERT INTO courses_per_students(courses_id,students_id)
VALUES (1,1),(1,2),(1,20),(1,4),(1,15),(2,16),(2,14),(2,4),(2,9),(4,11),(4,16),(4,5),(4,6),(7,21),(8,18),(7,13),(6,16),(6,22);

INSERT INTO courses_per_trainers(courses_id,trainers_id)
VALUES (1,1),(1,3),(1,5),(6,3),(6,5),(6,4),(2,7),(2,9),(8,7),(8,9),(3,9),(3,2),(4,6),(4,7),(5,4),(5,2),(6,8);

INSERT INTO assignments (title,description,sub_date_time)
VALUE  ("Responive Website","Design A Responsive Layout","2021-4-2"),("Calculator","Create a calculator with functional programming","2021-4-20"),
("Individual Project",null,"2021-6-2"),("Team Project",null,"2021-9-1"),("Portofolio","Build your own portofolio","2021-4-15"),
("Game","Create your own game","2021-9-10"),("Form Validation",null,"2021-4-1"),("Quiz app","Create your own Quizz","2021-5-2"); 

INSERT INTO students_per_assignments(total_mark,oral_mark,student_id,assignment_id,course_id)
VALUE (90,10,1,1,1),(70,20,2,1,1),(80,10,15,2,1),(90,5,6,3,4),(100,20,16,4,2),(75,15,5,3,4),(65,5,11,3,2),(80,19,2,2,1);
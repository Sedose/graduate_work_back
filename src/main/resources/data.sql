SET @course_kotlin_1_description = "This course aims to share with you the power and the beauty of Kotlin. We'll have a basic overview of the language, as well as a discussion of many corner cases, especially concerning Java interoperability.";
SET @course_algo_1_description = "This specialization is a mix of theory and practice: you will learn algorithmic techniques for solving various computational problems and will implement about 100 algorithmic coding problems in a programming language of your choice.";

DROP TABLE IF EXISTS lessons_students;
DROP TABLE IF EXISTS lessons_student_groups;
DROP TABLE IF EXISTS max_attendances;
DROP TABLE IF EXISTS student_groups;
DROP TABLE IF EXISTS user_coordinates;
DROP TABLE IF EXISTS user_attendances;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS university;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS university_employees;
DROP TABLE IF EXISTS users_settings;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS settings;

CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    last_name VARCHAR(255),
    UNIQUE KEY `full_name` (`first_name`, `middle_name`, `last_name`)
) ENGINE=InnoDB;

CREATE TABLE students (
    id INT UNSIGNED PRIMARY KEY,
    group_id INT UNSIGNED NOT NULL
) ENGINE=InnoDB;

CREATE TABLE university_employees (
    id INT UNSIGNED PRIMARY KEY,
    academic_position VARCHAR(255) NOT NULL,
    administrative_posts VARCHAR(255) NOT NULL,
    academic_degree VARCHAR(255) NOT NULL,
    academic_title VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE student_groups (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    group_leader_id INT UNSIGNED,
    group_curator_id INT UNSIGNED,
    department_id INT UNSIGNED,
    course_number INT UNSIGNED NOT NULL,
    specialty_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE university (
    id INT UNSIGNED PRIMARY KEY,
    city_id INT UNSIGNED,
    name VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE department (
    id INT UNSIGNED PRIMARY KEY,
    university_id INT UNSIGNED,
    name VARCHAR(255),
    description VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE `courses` (
  `id` int(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` timestamp NOT NULL,
  `end_date` timestamp NOT NULL,
  `lecturer_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `user_attendances` (
  `id` int(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `course_id` int(10) UNSIGNED NOT NULL,
  `registered_by` int(10) UNSIGNED NOT NULL,
  `registered_timestamp` timestamp NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `settings`(
    `code` varchar(255) COLLATE utf8mb4_unicode_ci PRIMARY KEY,
    `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `default_value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `users_settings`(
    `code` varchar(255) COLLATE utf8mb4_unicode_ci PRIMARY KEY,
    `user_id` int(10) UNSIGNED NOT NULL,
    `value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `max_attendances`(
    `student_group_id` INT UNSIGNED,
    `course_id` INT UNSIGNED,
    `max_attendances` INT UNSIGNED,
    PRIMARY KEY (`student_group_id`, `course_id`)
) ENGINE=InnoDB;

ALTER TABLE users_settings
ADD FOREIGN KEY (code) REFERENCES settings(code)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE `max_attendances`
ADD FOREIGN KEY (student_group_id) REFERENCES student_groups(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE `max_attendances`
ADD FOREIGN KEY (course_id) REFERENCES courses(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE users_settings
ADD FOREIGN KEY (user_id) REFERENCES users(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE department
ADD FOREIGN KEY (university_id) REFERENCES university(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE student_groups
ADD FOREIGN KEY (department_id) REFERENCES department(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE student_groups
ADD FOREIGN KEY (group_leader_id) REFERENCES students(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE student_groups
ADD FOREIGN KEY (group_curator_id) REFERENCES university_employees(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE students
ADD FOREIGN KEY (id) REFERENCES users(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE university_employees
ADD FOREIGN KEY (id) REFERENCES users(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_attendances
ADD FOREIGN KEY (user_id) REFERENCES users(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_attendances
ADD FOREIGN KEY (course_id) REFERENCES courses(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE courses
ADD FOREIGN KEY (lecturer_id) REFERENCES university_employees(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_attendances
ADD FOREIGN KEY (registered_by) REFERENCES university_employees(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO users VALUES(
    1,
    'Edharezenva.Avuzi@cs.khpi.edu.ua',
#     'LECTURER',
#     'STUDENT',
    'TRAINING_REPRESENTATIVE',
    'ACTIVE',
    NULL,
    NULL,
    NULL
),
(
    2,
    'alexandra@gmail.com',
    'STUDENT',
    'ACTIVE',
    NULL,
    NULL,
    NULL
),
(
    3,
    'lamborghini@gmail.com',
    'STUDENT',
    'ACTIVE',
    NULL,
    NULL,
    NULL
),(
    4,
    'always_lecturer@gmail.com',
    'STUDENT',
    'ACTIVE',
    NULL,
    NULL,
    NULL
),(
    5,
    'somonto@gmail.com',
    'TRAINING_REPRESENTATIVE',
    'ACTIVE',
    NULL,
    NULL,
    NULL
),

(
    6,
    'mail1',
    'LECTURER',
    'ACTIVE',
    'Дмитро',
    'Едуардович',
    'Двухглавов'
),(
    7,
    'mail2',
    'STUDENT',
    'ACTIVE',
    'Світлана',
    'Романівна',
    'Жернова'
),(
    8,
    'mail3',
    'STUDENT',
    'ACTIVE',
    'Олександра',
    'Миколаївна',
    'Мальцева'
),(
    9,
    'mail4',
    'STUDENT',
    'ACTIVE',
    'Катерина',
    'Вячеславівна',
    'Яковлева'
),(
    10,
    'mail5',
    'STUDENT',
    'ACTIVE',
    'Анастасія',
    'Олександрівна',
    'Лужна'
),(
    11,
    'mail6',
    'STUDENT',
    'ACTIVE',
    'Дмитро',
    'Андрійович',
    'Андоньєв'
),(
    12,
    'mail7',
    'STUDENT',
    'ACTIVE',
    'Яна',
    'Вячеславівна',
    'Кабак'
),(
    13,
    'mail8',
    'LECTURER',
    'ACTIVE',
    'Нікіта',
    'Сергійович',
    'Багацький'
),(
    14,
    'mail9',
    'STUDENT',
    'ACTIVE',
    'Давид',
    'Федорович',
    'Дахіна'
),(
    15,
    'mail10',
    'STUDENT',
    'ACTIVE',
    'Андрій',
    'Валерійович',
    'Шаталов'
),(
    16,
    'mail11',
    'STUDENT',
    'ACTIVE',
    'Кирило',
    'Дмитрович',
    'Рудковський'
),(
   17,
   'mail12',
   'STUDENT',
   'ACTIVE',
   'Са',
   NULL,
   'До'
),(
   18,
   'mail13',
   'STUDENT',
   'ACTIVE',
   'Чинь',
   'Ла',
   NULL
),(
   19,
   'mail14',
   'STUDENT',
   'ACTIVE',
   'Сунь',
   NULL,
   NULL
);

INSERT INTO university_employees VALUES
(1, 'Super lecturer', 'Super lecturer', 'Professor track 3', 'Professor');

INSERT INTO courses VALUES
(1, 'Kotlin essentials', @course_kotlin_1_description,  date("2020-09-04 12:12:12"), date("2021-06-04 12:12:12"), 1),
(2, 'Algorithms and data structures', @course_algo_1_description,  date("2020-09-04 12:12:12"), date("2021-06-04 12:12:12"), 1);

INSERT INTO university VALUES
(1, 1, 'KhPI');

INSERT INTO department VALUES
(1, 1, 'KN', 'KN description'),
(2, 1, 'IT', 'IT description');

INSERT INTO student_groups VALUES
(1, 'KN-217a', NULL, NULL, 1, 3, 'KN'),
(2, 'KN-222f', NULL, NULL, 1, 5, 'PI'),
(3, 'KN-111e', NULL, NULL, 1, 2, 'KN');

INSERT INTO students VALUES
(2, 2),
(3, 3),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1);

INSERT INTO `settings` VALUES
('MIN_STUDENT_ATTENDANCE_FILE_UPLOAD_INTERVAL', 'Sets the minimum period in seconds between 2 file uploads for user', '3600');
INSERT INTO `users_settings` VALUES
('MIN_STUDENT_ATTENDANCE_FILE_UPLOAD_INTERVAL', 1, '3600');

INSERT INTO `max_attendances` VALUES
    (1, 1, 100),
    (2, 2, 150),
    (3, 2, 200)
;

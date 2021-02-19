DROP TABLE IF EXISTS student_attendances;
DROP TABLE IF EXISTS lessons_students;
DROP TABLE IF EXISTS lessons_student_groups;
DROP TABLE IF EXISTS student_groups;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS university_employees;
DROP TABLE IF EXISTS user_coordinates;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS university;
DROP TABLE IF EXISTS course;

CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE user_coordinates (
    user_id INT UNSIGNED NOT NULL PRIMARY KEY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);

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

CREATE TABLE lessons (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    name VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    description TEXT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE lessons_student_groups (
    lesson_id INT UNSIGNED,
    student_group_id INT UNSIGNED,
    PRIMARY KEY (lesson_id, student_group_id)
) ENGINE=InnoDB;

CREATE TABLE lessons_students (
    lesson_id INT UNSIGNED,
    student_id INT UNSIGNED,
    status VARCHAR(255), -- e.g. ATTENDED | LEFT_BEFORE_LESSON_END
    PRIMARY KEY (lesson_id, student_id)
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

CREATE TABLE `course` (
  `id` int(11) UNSIGNED PRIMARY KEY,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` timestamp NOT NULL,
  `end_date` int(11) NOT NULL,
  `lecturer_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB;

CREATE TABLE `student_attendances` (
  `id` int(10) UNSIGNED PRIMARY KEY,
  `student_id` int(10) UNSIGNED NOT NULL,
  `course_id` int(10) UNSIGNED NOT NULL,
  `timestamp` timestamp NOT NULL
) ENGINE=InnoDB;




ALTER TABLE department
ADD FOREIGN KEY (university_id) REFERENCES university(id)
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

ALTER TABLE lessons_student_groups
ADD FOREIGN KEY (lesson_id) REFERENCES lessons(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE lessons_student_groups
ADD FOREIGN KEY (student_group_id) REFERENCES student_groups(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE lessons_students
ADD FOREIGN KEY (lesson_id) REFERENCES lessons(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE lessons_students
ADD FOREIGN KEY (student_id) REFERENCES students(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE user_coordinates
ADD FOREIGN KEY (user_id) REFERENCES users(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE student_attendances
ADD FOREIGN KEY (student_id) REFERENCES students(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE student_attendances
ADD FOREIGN KEY (course_id) REFERENCES course(id)
ON UPDATE RESTRICT ON DELETE RESTRICT;

INSERT INTO users VALUES(
    1,
    'Edharezenva.Avuzi@cs.khpi.edu.ua',
    'Edharezenva.Avuzi@cs.khpi.edu.ua',
    'Edharezenva.Avuzi@cs.khpi.edu.ua',
    'Edharezenva.Avuzi@cs.khpi.edu.ua',
    'STUDENT',
    'ACTIVE'
),
(
    2,
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    'STUDENT',
    'ACTIVE'
),
(
    3,
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    'STUDENT',
    'ACTIVE'
),(
    4,
    'sandora@gmail.com',
    'sandora@gmail.com',
    'sandora@gmail.com',
    'sandora@gmail.com',
    'LECTURER',
    'ACTIVE'
),(
    5,
    'somonto@gmail.com',
    'somonto@gmail.com',
    'somonto@gmail.com',
    'somonto@gmail.com',
    'TRAINING_REPRESENTATIVE',
    'ACTIVE'
);

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
(1, 1),
(2, 2),
(3, 3);

INSERT INTO user_coordinates VALUES
(1, 49.999761199999995, 36.2435298),
(2, 49.999761199999995, 36.2435298),
(3, 49.999761199999995, 36.2435298);

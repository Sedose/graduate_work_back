DROP TABLE IF EXISTS student_groups;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS university_employees;
DROP TABLE IF EXISTS user_coordinates;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS scheduled_lessons;
DROP TABLE IF EXISTS scheduled_lessons_student_groups;

-- <Юзеры, кто логинятся на сайт. Для авторизации, аутентификации.>
CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- <Чисто для Эдгара>
CREATE TABLE user_coordinates (
    user_id INT UNSIGNED NOT NULL PRIMARY KEY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);
-- </Чисто для Эдгара>

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
    course_number INT UNSIGNED NOT NULL,
    specialty_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE lessons (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    description TEXT NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE
) ENGINE=InnoDB;

CREATE TABLE lessons_student_groups (
    lesson_id INT UNSIGNED,
    student_group_id INT UNSIGNED,
    PRIMARY KEY (lesson_id, student_group_id)
) ENGINE=InnoDB;

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

ALTER TABLE user_coordinates
ADD FOREIGN KEY (user_id) REFERENCES users(id)
ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO users VALUES(
    1,
    'sabini@gmail.com',
    'sabini@gmail.com',
    'sabini@gmail.com',
    'avuzia@gmail.com',
    '$2a$10$Xk3.7QpTPEx5.NusW9zWNe/ClzuZlL6OdwH1Er8DWVpdA0ubGDBcu',
    'STUDENT',
    'ACTIVE'
),
(
    2,
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    'alexandra@gmail.com',
    '$2a$10$Xk3.7QpTPEx5.NusW9zWNe/ClzuZlL6OdwH1Er8DWVpdA0ubGDBcu',
    'STUDENT',
    'ACTIVE'
),
(
    3,
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    'lamborghini@gmail.com',
    '$2a$10$Xk3.7QpTPEx5.NusW9zWNe/ClzuZlL6OdwH1Er8DWVpdA0ubGDBcu',
    'STUDENT',
    'ACTIVE'
),(
    4,
    'sandora@gmail.com',
    'sandora@gmail.com',
    'sandora@gmail.com',
    'sandora@gmail.com',
    '$2a$10$Xk3.7QpTPEx5.NusW9zWNe/ClzuZlL6OdwH1Er8DWVpdA0ubGDBcu',
    'LECTURER',
    'ACTIVE'
),(
    5,
    'somonto@gmail.com',
    'somonto@gmail.com',
    'somonto@gmail.com',
    'somonto@gmail.com',
    '$2a$10$Xk3.7QpTPEx5.NusW9zWNe/ClzuZlL6OdwH1Er8DWVpdA0ubGDBcu',
    'TRAINING_REPRESENTATIVE',
    'ACTIVE'
);

INSERT INTO student_groups VALUES
(1, 'KN-217a', NULL, NULL, 3, 'KN'),
(2, 'KN-222f', NULL, NULL, 5, 'PI'),
(3, 'KN-111e', NULL, NULL, 2, 'KN');

INSERT INTO students VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO user_coordinates VALUES
(1, 49.999761199999995, 36.2435298),
(2, 49.999761199999995, 36.2435298),
(3, 49.999761199999995, 36.2435298);

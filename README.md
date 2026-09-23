# Student Management System

A desktop-based Student Management System developed using Java Swing, JDBC, and Oracle Database.

## Project Overview

The Student Management System is a Java desktop application designed to manage student records through a user-friendly graphical interface.

The application provides functionality for adding, viewing, searching, updating, and deleting student records.

## Features

- Add Student
- View All Students
- Search Student
- Update Student
- Delete Student
- CGPA Management
- Refresh Student Records
- Export Student Data to CSV
- Change Password
- Logout

## Technologies Used

- Java
- Java Swing
- JDBC
- Oracle Database
- SQL
- VS Code
- Oracle JDBC Driver (ojdbc11)

## Database

The project uses Oracle Database for storing student information.

### Student Table

```sql
CREATE TABLE STUDENT (
    STUDENT_ID NUMBER(5) PRIMARY KEY,
    NAME VARCHAR2(50),
    AGE NUMBER(2),
    GENDER VARCHAR2(10),
    COURSE VARCHAR2(30),
    PHONE VARCHAR2(10),
    CGPA NUMBER(4,2)
);
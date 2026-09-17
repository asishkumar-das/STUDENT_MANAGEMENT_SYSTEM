import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // =====================================================
    // ADD STUDENT
    // =====================================================
    public void addStudent(Student student) {

        String sql = "INSERT INTO STUDENT " +
                "(STUDENT_ID, NAME, AGE, GENDER, COURSE, PHONE, CGPA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database Connection Failed");
                return;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getPhone());
            ps.setDouble(7, student.getCgpa());

            ps.executeUpdate();

            System.out.println("Student Added Successfully");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // VIEW ALL STUDENTS
    // =====================================================
    public List<Student> viewAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM STUDENT ORDER BY STUDENT_ID";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return students;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("STUDENT_ID"),
                        rs.getString("NAME"),
                        rs.getInt("AGE"),
                        rs.getString("GENDER"),
                        rs.getString("COURSE"),
                        rs.getString("PHONE"),
                        rs.getDouble("CGPA")
                );

                students.add(student);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }


    // =====================================================
    // SEARCH STUDENT BY ID
    // =====================================================
    public Student searchStudentById(int studentId) {

        String sql =
                "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return null;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student student = new Student(
                        rs.getInt("STUDENT_ID"),
                        rs.getString("NAME"),
                        rs.getInt("AGE"),
                        rs.getString("GENDER"),
                        rs.getString("COURSE"),
                        rs.getString("PHONE"),
                        rs.getDouble("CGPA")
                );

                rs.close();
                ps.close();
                con.close();

                return student;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // SEARCH BY ID, NAME OR COURSE
    // =====================================================
    public List<Student> searchStudents(String keyword) {

        List<Student> students = new ArrayList<>();

        String sql =
                "SELECT * FROM STUDENT " +
                "WHERE TO_CHAR(STUDENT_ID) LIKE ? " +
                "OR UPPER(NAME) LIKE UPPER(?) " +
                "OR UPPER(COURSE) LIKE UPPER(?) " +
                "ORDER BY STUDENT_ID";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return students;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            String searchValue = "%" + keyword + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("STUDENT_ID"),
                        rs.getString("NAME"),
                        rs.getInt("AGE"),
                        rs.getString("GENDER"),
                        rs.getString("COURSE"),
                        rs.getString("PHONE"),
                        rs.getDouble("CGPA")
                );

                students.add(student);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }


    // =====================================================
    // UPDATE STUDENT
    // =====================================================
    public void updateStudent(
            int studentId,
            String name,
            int age,
            String gender,
            String course,
            String phone,
            double cgpa) {

        String sql =
                "UPDATE STUDENT SET " +
                "NAME = ?, " +
                "AGE = ?, " +
                "GENDER = ?, " +
                "COURSE = ?, " +
                "PHONE = ?, " +
                "CGPA = ? " +
                "WHERE STUDENT_ID = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, course);
            ps.setString(5, phone);
            ps.setDouble(6, cgpa);
            ps.setInt(7, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Updated Successfully");
            } else {
                System.out.println("Student Not Found");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================
    public void deleteStudent(int studentId) {

        String sql =
                "DELETE FROM STUDENT WHERE STUDENT_ID = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Student Not Found");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // CHECK DUPLICATE STUDENT ID
    // =====================================================
    public boolean studentExists(int studentId) {

        String sql =
                "SELECT STUDENT_ID " +
                "FROM STUDENT " +
                "WHERE STUDENT_ID = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean exists = rs.next();

            rs.close();
            ps.close();
            con.close();

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // LOGIN
    // =====================================================
    public boolean login(String username, String password) {

        String sql =
                "SELECT * FROM LOGIN " +
                "WHERE USERNAME = ? " +
                "AND PASSWORD = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            boolean result = rs.next();

            rs.close();
            ps.close();
            con.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // CHANGE PASSWORD
    // =====================================================
    public boolean changePassword(
            String username,
            String oldPassword,
            String newPassword) {

        String sql =
                "UPDATE LOGIN SET PASSWORD = ? " +
                "WHERE USERNAME = ? " +
                "AND PASSWORD = ?";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, oldPassword);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =====================================================
    // GET TOTAL STUDENTS
    // =====================================================
    public int getTotalStudents() {

        String sql = "SELECT COUNT(*) FROM STUDENT";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return 0;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int total = rs.getInt(1);

                rs.close();
                ps.close();
                con.close();

                return total;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // GET STUDENTS BY COURSE
    // =====================================================
    public int getStudentsByCourse(String course) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM STUDENT " +
                "WHERE UPPER(COURSE) = UPPER(?)";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return 0;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, course);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int total = rs.getInt(1);

                rs.close();
                ps.close();
                con.close();

                return total;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // GET AVERAGE CGPA
    // =====================================================
    public double getAverageCgpa() {

        String sql =
                "SELECT NVL(AVG(CGPA), 0) " +
                "FROM STUDENT";

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                return 0.0;
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double average = rs.getDouble(1);

                rs.close();
                ps.close();
                con.close();

                return average;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}
public class Student {

    private int studentId;
    private String name;
    private int age;
    private String gender;
    private String course;
    private String phone;
    private double cgpa;

    public Student(int studentId, String name, int age, String gender,
                   String course, String phone, double cgpa) {

        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.phone = phone;
        this.cgpa = cgpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public String getPhone() {
        return phone;
    }

    public double getCgpa() {
        return cgpa;
    }
}
/**
 * This project is to Maintain the Student records
 * Student informations like Student Id, Student Name, Student Age, Student
 * Course.
 * SMS_2
 */

public class Student {
    // private modifier used bcoz not used by any other class

    private int student_id;
    private String student_name;
    private int student_age;
    private String student_course;

    // Constructor used to pass values

    public Student(int student_id, String student_name, int student_age, String student_course) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_age = student_age;
        this.student_course = student_course;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void getStudent_age(int student_age) {
        this.student_age = student_age;
    }

    public int getStudent_age() {
        return student_age;
    }

    public void getStudent_course(String student_course) {
        this.student_course = student_course;
    }

    public String getStudent_course() {
        return student_course;
    }

    public String toString() {
        return student_id + "," + student_name + "," + student_age + "," + student_course;
    }
}


import java.util.ArrayList;
import java.util.Scanner;
public class SMS_Main {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean program = true;
        while (program) {
            System.out.println("-----STUDENT MANAGEMENT SYSTEM-----");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View all Students");
            System.out.println("Enter your Choice:");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("==Add Student==");
                    addStudent();
                    break;
                case 2:
                    System.out.println("==Update Student==");
                    updateStudent();
                    break;
                case 3:
                    System.out.println("==Delete Student==");
                    deleteStudent();
                    break;
                case 4:
                    System.out.println("==View Students==");
                    viewStudents();
                    break;
                case 5:
                    System.out.println("Thank You!");
                    program = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println();
            }
        }
    }

    public static void addStudent() {
        System.out.println("Enter Student ID: ");
        int student_id = scanner.nextInt();
        System.out.println("Enter Student Name: ");
        String student_name = scanner.next();
        System.out.println("Enter Student Age: ");
        int student_age = scanner.nextInt();
        System.out.println("Enter Student Course: ");
        String student_course = scanner.next();
        students.add(new Student(student_id, student_name, student_age, student_course));
        System.out.println("Student Added Successfully!");
    }
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
    private static void updateStudent() {
        if(students.isEmpty()){
            System.out.println("No Records Found.");
            return;
        }
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        for (Student student : students) {
            if (student.getStudent_id() == id) {
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new Age: ");
                int newAge = scanner.nextInt();
                System.out.print("Enter new Course: ");
                String newCourse = scanner.nextLine();
                students.set(students.indexOf(student), new Student(id, newName, newAge, newCourse));
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
    private static void deleteStudent() {
        if(students.isEmpty()){
            System.out.println("No Records Found.");
            return;
        }
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        for (Student student : students) {
            if (student.getStudent_id() == id) {
                students.remove(student);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
}
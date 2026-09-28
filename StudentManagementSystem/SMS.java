import java.util.Scanner;

/**
 * SMS
 */
public class SMS {
    static int id[] = new int[10];
    static String name[] = new String[10];
    static int age[] = new int[10];
    static String course[] = new String[10];
    static int count = 0;

    public static void main(String[] args) {
        boolean program = true;
        while (program) {
            Scanner sc = new Scanner(System.in);
            System.out.println("-------Student Management System-------");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View all Student");
            System.out.println("Enter Your Choice: ");
            int choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("---Add Student---:");
                    addStudent(sc);
                    break;
                case 2:
                    System.out.println("---Update Student---:");
                    updateStudent(sc);
                    break;
                case 3:
                    System.out.println("---Delete Student---:");
                    deleteStudent(sc);
                    break;
                case 4:
                    System.out.println("---Student Details---:");
                    viewStudent();
                    break;
                case 5:
                    System.out.println("Thank You!");
                    System.out.println();
                    program = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println();
            }
        }
    }

    public static void addStudent(Scanner sc) {
        System.out.println("Enter Student ID: ");
        id[count] = sc.nextInt();
        System.out.println("Enter Student Name: ");
        name[count] = sc.next();
        System.out.println("Enter Student Age: ");
        age[count] = sc.nextInt();
        System.out.println("Enter Student Course: ");
        course[count] = sc.next();
        count++;
        System.out.println("Student Added Successfully");
        System.out.println();
    }

    public static void updateStudent(Scanner sc) {
        if (count == 0) {
            System.out.println("No Student to Update");
            System.out.println();
            return;
        }
        System.out.println("Enter Student ID to Update: ");
        int updateId = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (id[i] == updateId) {
                System.out.println("Enter Student Name:");
                name[i] = sc.next();
                System.out.println("Enter Student Age:");
                age[i] = sc.nextInt();
                System.out.println("Enter Student Course:");
                course[i] = sc.next();
                System.out.println("Student Details Updated Successfully!");
                System.out.println();
                return;
            }
        }
        System.out.println("Student ID not found");
        System.out.println();
    }

    public static void deleteStudent(Scanner sc) {
        if (count == 0) {
            System.out.println("No Student to Delete");
            System.out.println();
            return;
        }
        System.out.println("Enter Student ID to Delete:");
        int deleteId = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (id[i] == deleteId) {
                for (int j = i; j < count - i; j++) {
                    id[j] = id[j + 1];
                    name[j] = name[j + 1];
                    age[j] = age[j + 1];
                    course[j] = course[j + 1];
                }
            }
            count--;
            System.out.println("Student Deleted Successfully!");
            System.out.println();
            return;
        }
        System.out.println("Student Not Found!");
        System.out.println();
    }
    public static void viewStudent(){
        if(count==0){
            System.out.println("No Student exist");
            System.out.println();
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("ID: "+ id[i]);
            System.out.println("Name: " + name[i]);
            System.out.println("Age: " + age[i]);
            System.out.println("Course: " + course[i]);
            System.out.println();
        }
    }
}
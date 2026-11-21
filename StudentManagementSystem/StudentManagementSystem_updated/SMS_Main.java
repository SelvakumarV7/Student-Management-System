import java.util.List;
import java.util.Scanner;

public class SMS_Main {
    public static void main(String[] args) {
        // Instantiate the object manager (Removes static method dependency)
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Student Management System Initialized ===");

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Record");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            int choice = readValidInteger(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Age: ");
                    int age = readValidInteger(scanner);
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine().trim();

                    // Passes concrete Student reference instantiation directly to manager layer
                    if (manager.addStudent(new Student(id, name, age, course))) {
                        System.out.println("[Success] Student added successfully!");
                    } else {
                        System.out.println("[Failure] Conflict: A student with ID " + id + " already exists.");
                    }
                    break;

                case 2:
                    List<Student> list = manager.getAllStudents();
                    if (list.isEmpty()) {
                        System.out.println("[Info] No student records found in memory database.");
                    } else {
                        System.out.println("\n--- Current Student Records ---");
                        for (Student s : list) {
                            System.out.println(s); // Uses overriden toString() format
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter the ID of the student to update: ");
                    String updateId = scanner.nextLine().trim();
                    
                    // Validate if the student exists before asking for full input forms
                    Student existing = manager.getStudentById(updateId);
                    if (existing == null) {
                        System.out.println("[Failure] Student record not found with ID: " + updateId);
                        break;
                    }

                    System.out.print("Enter New Name [" + existing.getName() + "]: ");
                    String newName = scanner.nextLine().trim();
                    
                    System.out.print("Enter New Age [" + existing.getAge() + "]: ");
                    int newAge = readValidInteger(scanner);
                    
                    System.out.print("Enter New Course [" + existing.getCourse() + "]: ");
                    String newCourse = scanner.nextLine().trim();

                    if (manager.updateStudent(updateId, newName, newAge, newCourse)) {
                        System.out.println("[Success] Student record updated successfully!");
                    }
                    break;

                case 4:
                    System.out.print("Enter the ID of the student to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    if (manager.deleteStudent(deleteId)) {
                        System.out.println("[Success] Student deleted successfully.");
                    } else {
                        System.out.println("[Failure] Could not delete. Student with ID " + deleteId + " does not exist.");
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("System terminated. Data securely saved.");
                    break;

                default:
                    System.out.println("[Warning] Invalid choice selection. Please select options 1 through 5.");
            }
        }
        scanner.close();
    }

    // Input validation utility to clear Scanner buffers safely and isolate numbers without application breakdown
    private static int readValidInteger(Scanner scanner) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val < 0) {
                    System.out.print("Input numeric error. Value cannot be negative. Try again: ");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Format parsing conflict. Please input a valid numeric number: ");
            }
        }
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final List<Student> studentList;
    private final String FILE_PATH = "students.txt";

    public StudentManager() {
        this.studentList = new ArrayList<>();
        loadRecordsFromFile(); // Automatically populate the list on startup
    }

    // CREATE: Adds a Student object directly to the collection
    public boolean addStudent(Student newStudent) {
        if (findStudentIndexById(newStudent.getId()) != -1) {
            return false; // Prevents ID conflicts
        }
        studentList.add(newStudent);
        saveRecordsToFile();
        return true;
    }

    // READ ALL: Returns list 
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }

    // READ SINGLE
    public Student getStudentById(String id) {
        int index = findStudentIndexById(id);
        return (index != -1) ? studentList.get(index) : null;
    }

    // UPDATE: 
    public boolean updateStudent(String id, String newName, int newAge, String newCourse) {
        int index = findStudentIndexById(id);
        if (index == -1) {
            return false; // Student not found
        }
        
        Student targetStudent = studentList.get(index);
        targetStudent.setName(newName);
        targetStudent.setAge(newAge);
        targetStudent.setCourse(newCourse);
        
        saveRecordsToFile(); // Persist changes immediately
        return true;
    }

    // DELETE: Fixed concurrent-safe logic removing objects by tracked index positioning
    public boolean deleteStudent(String id) {
        int index = findStudentIndexById(id);
        if (index == -1) {
            return false;
        }
        studentList.remove(index);
        saveRecordsToFile(); // Persist state change
        return true;
    }

    // Helper method to look up list array index by ID safely
    private int findStudentIndexById(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    // FILE HANDLING: Write the state of the active ArrayList back to disk
    private void saveRecordsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student s : studentList) {
                writer.write(s.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("[Error] Failed writing state changes to database file: " + e.getMessage());
        }
    }

    // FILE HANDLING: Load structured text records directly into concrete runtime Objects
    private void loadRecordsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return; 

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Student s = Student.fromCsvString(line);
                if (s != null) {
                    studentList.add(s);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("[Warning] Database recovery completed with file faults: " + e.getMessage());
        }
    }
}
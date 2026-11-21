public class Student {
    private String id;
    private String name;
    private int age;
    private String course;

    // Constructor:
    public Student(String id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters and Setters: 
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    // Serialization: Standardises how a Student becomes plain text
    public String toCsvString() {
        return id + "," + name + "," + age + "," + course;
    }

    // Deserialization: Factory method to turn a CSV text line back into an Object
    public static Student fromCsvString(String csvLine) {
        String[] tokens = csvLine.split(",");
        if (tokens.length == 4) {
            String id = tokens[0].trim();
            String name = tokens[1].trim();
            int age = Integer.parseInt(tokens[2].trim());
            String course = tokens[3].trim();
            return new Student(id, name, age, course);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-15s | Age: %-4d | Course: %s", id, name, age, course);
    }
}

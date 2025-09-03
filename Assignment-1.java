// Superclass
class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}

// Subclass Student
class Student extends User {
    int studentId;

    Student(String name, String email, int studentId) {
        super(name, email);
        this.studentId = studentId;
    }

    @Override
    void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Student Email: " + email);
        System.out.println("Student ID: " + studentId);
    }

    void study() {
        System.out.println(name + " is studying. (Student ID: " + studentId + ")");
    }
}

// Subclass Instructor
class Instructor extends User {
    double salary;

    Instructor(String name, String email, double salary) {
        super(name, email);
        this.salary = salary;
    }

    @Override
    void displayInfo() {
        System.out.println("Instructor Name: " + name);
        System.out.println("Instructor Email: " + email);
        System.out.println("Salary: $" + salary);
    }

    void teach() {
        System.out.println(name + " is teaching. (Salary: $" + salary + ")");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        User[] users = {
            new Student("Alice", "alice@example.com", 101),
            new Instructor("Dr. Bob", "bob@example.com", 55000)
        };

        for (User u : users) {
            u.displayInfo();
            if (u instanceof Student) {
                ((Student) u).study();
            } else if (u instanceof Instructor) {
                ((Instructor) u).teach();
            }
            System.out.println();
        }
    }
}

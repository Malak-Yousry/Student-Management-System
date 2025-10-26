
package studentsss;
import java.util.Scanner;

        
public class Studentsss {

   

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.loadFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();

                    Student s = new Student(id, name, age, gender, dept, gpa);
                    manager.addStudent(s);
                    System.out.println("✅ Student added.");
                }

                case 2 -> {
                    System.out.println("\n--- All Students ---");
                    for (Student s : manager.getAllStudents()) {
                        System.out.println(s.getId() + " - " + s.getName());
                    }
                }

                case 3 -> {
                    System.out.print("Enter ID to search: ");
                    int id = scanner.nextInt();
                    Student s = manager.searchById(id);
                    if (s != null) {
                        System.out.println("Found: " + s.getName());
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                }

                case 4 -> {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    var results = manager.searchByName(name);
                    if (!results.isEmpty()) {
                        System.out.println("Matches:");
                        for (Student s : results) {
                            System.out.println(s.getId() + " - " + s.getName());
                        }
                    } else {
                        System.out.println("❌ No matches found.");
                    }
                }

                case 5 -> {
                    System.out.print("Enter ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Student s = manager.searchById(id);
                    if (s != null) {
                        System.out.print("New Name: ");
                        s.setName(scanner.nextLine());
                        System.out.print("New Age: ");
                        s.setAge(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("New Gender: ");
                        s.setGender(scanner.nextLine());
                        System.out.print("New Department: ");
                        s.setDepartment(scanner.nextLine());
                        System.out.print("New GPA: ");
                        s.setGpa(scanner.nextDouble());
                        scanner.nextLine();

                        manager.updateStudent(s);
                        System.out.println("✅ Student updated.");
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                }

                case 6 -> {
                    System.out.print("Enter ID to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteStudent(id);
                    System.out.println("✅ Student deleted.");
                }

                case 7 -> {
                    running = false;
                    System.out.println("👋 Exiting...");
                }

                default -> System.out.println("❌ Invalid choice.");
            }
        }

        scanner.close();
    }
}
    }
    
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, salary=%.2f}", name, age, salary);
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Employee> employees = Arrays.asList(
                new Employee("Anu", 20, 50000),
                new Employee("Bhanu", 45, 6000),
                new Employee("Charu", 38, 14000),
                new Employee("Eva", 40, 78000),
                new Employee("Ishmeet", 18, 85000)
        );

        while (true) {
            System.out.println("\nChoose Sorting Option:");
            System.out.println("1. Sort by Name");
            System.out.println("2. Sort by Age");
            System.out.println("3. Sort by Salary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            List<Employee> sortedList = new ArrayList<>(employees);

            switch (choice) {
                case 1:
                    sortedList = employees.stream()
                            .sorted(Comparator.comparing(e -> e.name))
                            .collect(Collectors.toList());
                    System.out.println("\nSorted by Name:");
                    break;

                case 2:
                    sortedList = employees.stream()
                            .sorted(Comparator.comparingInt(e -> e.age))
                            .collect(Collectors.toList());
                    System.out.println("\nSorted by Age:");
                    break;

                case 3:
                    System.out.print("Choose Salary Sorting Order (1 for Ascending, 2 for Descending): ");
                    int order = scanner.nextInt();
                    
                    sortedList = employees.stream()
                            .sorted(order == 1 
                                    ? Comparator.comparingDouble(e -> e.salary) 
                                    : Comparator.comparingDouble(e -> -e.salary))
                            .collect(Collectors.toList());

                    System.out.println(order == 1 ? "\nSorted by Salary (Ascending):" : "\nSorted by Salary (Descending):");

                    // Asking for filter criteria
                    System.out.print("Enter the salary threshold: ");
                    double threshold = scanner.nextDouble();
                    
                    System.out.print("Do you want employees (1) Above or (2) Below this salary? ");
                    int filterChoice = scanner.nextInt();

                    sortedList = sortedList.stream()
                            .filter(e -> (filterChoice == 1 ? e.salary >= threshold : e.salary <= threshold))
                            .collect(Collectors.toList());

                    System.out.println(filterChoice == 1 ? "\nEmployees with salary above or equal to " + threshold + ":" 
                                                          : "\nEmployees with salary below or equal to " + threshold + ":");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            sortedList.forEach(System.out::println);
        }
    }
}
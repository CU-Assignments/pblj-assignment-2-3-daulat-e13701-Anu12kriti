import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    public String toString() {
        return name + " - Marks: " + marks;
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Anu", 85),
            new Student("Bhanu", 72),
            new Student("Charu", 90),
            new Student("Donkey", 65),
            new Student("Emu", 88)
        );
        List<String> topStudents = students.stream()
            .filter(s -> s.marks > 75) 
            .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks)) 
            .map(s -> s.name) 
            .collect(Collectors.toList()); 

        System.out.println("Students scoring above 75% (sorted by marks): " + topStudents);
    }
}
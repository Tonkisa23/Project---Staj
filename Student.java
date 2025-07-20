package bg.tu_varna.s22621643.Model;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    public String name;
    public String facultyNumber;
    public String specialization;
    public String group;
    public int year;
    public Status status;
    public Map<String, Integer> grades = new HashMap<>();
    public Set<String> enrolledCourses = new HashSet<>();

    public Student(String name, String facultyNumber, int year, String specialization, String group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.year = year;
        this.specialization = specialization;
        this.group = group;
        this.status = Status.ENROLLED;
    }

    public double averageGrade() {
        int total = 0, count = 0;
        for (String course : enrolledCourses) {
            total += grades.getOrDefault(course, 2);
            count++;
        }
        return count == 0 ? 0.0 : total / (double) count;
    }
}

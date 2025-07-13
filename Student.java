package oop1.project.s22621643;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    String name;
    int facultyNumber;
    int course;
    String specialty;
    int group;
    String status;
    double averageGrade;
    Map<String, Integer> grades = new HashMap<>();
    List<String> enrolledCourses = new ArrayList<>();

    public Student(String name, int facultyNumber, String specialty, int group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.course = 1;
        this.specialty = specialty;
        this.group = group;
        this.status = "записан";
        this.averageGrade = 0.0;
    }

    public void advance() {
        if (status.equals("записан") && canAdvance()) {
            course++;
        } else {
            System.out.println("Студентът не може да премине в следващ курс.");
        }
    }

    public boolean canAdvance() {
        long failedExams = enrolledCourses.stream().filter(c -> !grades.containsKey(c) || grades.get(c) < 3).count();
        return failedExams <= 2;
    }

    public void change(String option, String value) {
        if (status.equals("прекъснал")) {
            System.out.println("Прекъснал студент не може да променя данните си.");
            return;
        }
        switch (option) {
            case "program":
                this.specialty = value;
                break;
            case "group":
                this.group = Integer.parseInt(value);
                break;
            case "year":
                System.out.println("Прехвърляне в курс е възможно само чрез advance.");
                break;
            default:
                System.out.println("Невалидна опция.");
        }
    }

    public void graduate() {
        if (grades.values().stream().allMatch(g -> g >= 3)) {
            status = "завършил";
        } else {
            System.out.println("Студентът не може да завърши.");
        }
    }

    public void interrupt() {
        status = "прекъснал";
    }

    public void resume() {
        if (status.equals("прекъснал")) {
            status = "записан";
        }
    }

    @Override
    public String toString() {
        return name + " (" + facultyNumber + ") - " + specialty + ", група: " + group + ", курс: " + course + ", статус: " + status;
    }
}
package bg.tu_varna.s22621643.Core;

import bg.tu_varna.s22621643.Builder.StudentBuilder;
import bg.tu_varna.s22621643.Factory.CourseFactory;
import bg.tu_varna.s22621643.Model.*;

import java.io.*;
import java.util.*;

public class StudentSystem {
    private static StudentSystem instance;
    public static StudentSystem getInstance() {
        if (instance == null) instance = new StudentSystem();
        return instance;
    }

    Map<String, Student> students = new HashMap<>();
    Map<String, Specialization> specializations = new HashMap<>();

    public void enroll(String fn, String program, String group, String name) {
        if (!specializations.containsKey(program)) specializations.put(program, new Specialization(program));
        Student s = new StudentBuilder()
                .setName(name).setFacultyNumber(fn).setSpecialization(program).setGroup(group).build();
        students.put(fn, s);
    }

    public void advance(String fn) {
        Student s = students.get(fn);
        if (s.status != Status.ENROLLED) return;
        Specialization spec = specializations.get(s.specialization);
        boolean canAdvance = spec != null && spec.getCourses().stream()
                .filter(c -> c.courseYear < s.year && c.type == CourseType.MANDATORY)
                .allMatch(c -> s.grades.getOrDefault(c.name, 2) > 2);
        if (canAdvance) s.year++;
    }

    public void change(String fn, String option, String value) {
        Student s = students.get(fn);
        if (s.status != Status.ENROLLED) return;
        switch (option) {
            case "group" -> s.group = value;
            case "year" -> {
                int newYear = Integer.parseInt(value);
                if (newYear == s.year + 1) advance(fn);
            }
            case "program" -> {
                Specialization newSpec = specializations.get(value);
                boolean eligible = newSpec != null && newSpec.getCourses().stream()
                        .filter(c -> c.courseYear < s.year && c.type == CourseType.MANDATORY)
                        .allMatch(c -> s.grades.getOrDefault(c.name, 2) > 2);
                if (eligible) s.specialization = value;
            }
        }
    }

    public void graduate(String fn) {
        Student s = students.get(fn);
        if (s.enrolledCourses.stream().allMatch(c -> s.grades.getOrDefault(c, 2) > 2)) {
            s.status = Status.GRADUATED;
        }
    }

    public void interrupt(String fn) { students.get(fn).status = Status.INTERRUPTED; }
    public void resume(String fn) { students.get(fn).status = Status.ENROLLED; }

    public void print(String fn) {
        Student s = students.get(fn);
        System.out.printf("%s (%s), Year: %d, Program: %s, Group: %s, Status: %s\n",
                s.name, fn, s.year, s.specialization, s.group, s.status);
    }

    public void printall(String program, int year) {
        students.values().stream()
                .filter(s -> s.specialization.equals(program) && s.year == year)
                .forEach(s -> print(s.facultyNumber));
    }

    public void enrollin(String fn, String course) {
        Student s = students.get(fn);
        if (s.status != Status.ENROLLED) return;
        s.enrolledCourses.add(course);
    }

    public void addgrade(String fn, String course, int grade) {
        Student s = students.get(fn);
        if (s.enrolledCourses.contains(course)) s.grades.put(course, grade);
    }

    public void protocol(String course) {
        System.out.println("Protocol for course: " + course);
        students.values().stream()
                .filter(s -> s.enrolledCourses.contains(course))
                .sorted(Comparator.comparing(s -> s.facultyNumber))
                .forEach(s -> System.out.println(s.facultyNumber + " " + s.name));
    }

    public void report(String fn) {
        Student s = students.get(fn);
        System.out.println("Report for: " + s.name);
        for (String course : s.enrolledCourses) {
            if (s.grades.containsKey(course)) System.out.printf("✔ %s: %d\n", course, s.grades.get(course));
            else System.out.printf("✘ %s: not passed\n", course);
        }
        System.out.printf("Average grade: %.2f\n", s.averageGrade());
    }

    public void addCourse(String program, String courseName, String type, int year) {
        Course course = CourseFactory.create(courseName, type, year);
        Specialization spec = specializations.computeIfAbsent(program, Specialization::new);
        spec.addCourse(course);
    }

    public void listCourses(String program) {
        Specialization spec = specializations.get(program);
        if (spec == null) System.out.println("❌ No such specialization.");
        else spec.getCourses().forEach(c -> System.out.printf("- %s (%s, year %d)\n", c.name, c.type, c.courseYear));
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            oos.writeObject(specializations);
        }
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (Map<String, Student>) ois.readObject();
            specializations = (Map<String, Specialization>) ois.readObject();
        }
    }
}

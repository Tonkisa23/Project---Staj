package bg.tu_varna.s22621643.Builder;

import bg.tu_varna.s22621643.Model.Student;

public class StudentBuilder {
    private String name, fn, specialization, group;
    private int year = 1;

    public StudentBuilder setName(String name) { this.name = name; return this; }
    public StudentBuilder setFacultyNumber(String fn) { this.fn = fn; return this; }
    public StudentBuilder setYear(int year) { this.year = year; return this; }
    public StudentBuilder setSpecialization(String specialization) { this.specialization = specialization; return this; }
    public StudentBuilder setGroup(String group) { this.group = group; return this; }

    public Student build() {
        return new Student(name, fn, year, specialization, group);
    }
}
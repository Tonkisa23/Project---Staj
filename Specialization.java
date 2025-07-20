package bg.tu_varna.s22621643.Model;

import java.io.Serializable;
import java.util.*;

public class Specialization implements Serializable {
    String name;
    List<Course> courses = new ArrayList<>();

    public Specialization(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}

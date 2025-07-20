package bg.tu_varna.s22621643;

import java.io.Serializable;
import java.util.*;

public class Specialization implements Serializable {
    String name;
    public List<Course> courses = new ArrayList<>();

    public Specialization(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}

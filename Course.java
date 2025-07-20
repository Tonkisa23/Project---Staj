package bg.tu_varna.s22621643.Model;

import java.io.Serializable;

public class Course implements Serializable {
    public String name;
    public CourseType type;
    public int courseYear;

    public Course(String name, CourseType type, int courseYear) {
        this.name = name;
        this.type = type;
        this.courseYear = courseYear;
    }
}

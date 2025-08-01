package bg.tu_varna.s22621643.Factory;

import bg.tu_varna.s22621643.Model.CourseType;
import bg.tu_varna.s22621643.Model.Course;

public class CourseFactory {
    public static Course create(String name, String type, int courseYear) {
        return new Course(name, CourseType.valueOf(type.toUpperCase()), courseYear);
    }
}

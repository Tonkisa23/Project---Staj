package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class EnrollCommand implements Command {
    public void execute(String[] args) {
        StudentSystem.getInstance().enroll(args[1], args[2], args[3], args[4]);
    }
}

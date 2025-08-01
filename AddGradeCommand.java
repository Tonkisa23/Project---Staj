package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class AddGradeCommand implements Command {
    public void execute(String[] args) {
        StudentSystem.getInstance().addgrade(args[1], args[2], Integer.parseInt(args[3]));
    }
}

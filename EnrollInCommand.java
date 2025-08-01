package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class EnrollInCommand implements Command {
    public void execute(String[] args) { StudentSystem.getInstance().enrollin(args[1], args[2]); }
}

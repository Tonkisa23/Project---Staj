package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class ChangeCommand implements Command {
    public void execute(String[] args) { StudentSystem.getInstance().change(args[1], args[2], args[3]); }
}

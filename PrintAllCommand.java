package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class PrintAllCommand implements Command {
    public void execute(String[] args) {
        StudentSystem.getInstance().printall(args[1], Integer.parseInt(args[2]));
    }
}

package bg.tu_varna.s22621643.Command;

import bg.tu_varna.s22621643.Core.StudentSystem;

public class ProtocolCommand implements Command {
    public void execute(String[] args) { StudentSystem.getInstance().protocol(args[1]); }
}

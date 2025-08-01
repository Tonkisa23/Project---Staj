package bg.tu_varna.s22621643.Command;

import java.util.*;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    public void execute(String input) {
        String[] tokens = input.trim().split(" ");
        Command command = commands.get(tokens[0]);
        if (command != null) command.execute(tokens);
        else System.out.println("❓ Невалидна команда.");
    }
}

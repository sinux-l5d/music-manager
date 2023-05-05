package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;

import java.util.Arrays;

public class CommandFactory {
    private final LibraryStorage lib;
    private final String helpstring;
    public CommandFactory(LibraryStorage lib, String helpstring) {
        this.lib = lib;
        this.helpstring = helpstring;
    }

    public Command getCommand(String cmd) {
        if (cmd == null) return new InvalidCommand();

        var command = "";
        String[] args = new String[0];

        if (cmd.startsWith("lib ")) {
            var split = cmd.split(" ");
            command = split[0] + " " + split[1];
            args = Arrays.copyOfRange(split, 2, split.length);
        } else {
            command = cmd;
        }

        return switch (command) {
            case "lib add" -> new AddCommand(lib, args);
            case "lib list" -> new ListCommand(lib, args);
            case "lib size" -> new SizeCommand(lib);
            case "lib search" -> new SearchCommand(lib, args);
            case "lib remove" -> new RemoveCommand(lib, args);
            /*
            case "lib save" -> new SaveCommand(lib);
             */
            case "help" -> new HelpCommand(helpstring);
            case "quit" -> new QuitCommand();
            default -> new InvalidCommand();
        };
    }
}

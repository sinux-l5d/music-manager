package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.AppCommand.LibraryCommand.*;
import sh.sinux.musicmanager.AppCommand.PlaylistCommand.*;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;

import java.util.Arrays;

/**
 * @author Simon Leonard
 */
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

        if (cmd.startsWith("lib ") || cmd.startsWith("pl")) {
            var split = cmd.split(" ");
            command = split[0] + " " + split[1];
            args = Arrays.copyOfRange(split, 2, split.length);
        } else if (cmd.startsWith("select")) {
            var split = cmd.split(" ");
            command = split[0];
            args = new String[]{split[1]};
        } else {
            command = cmd;
        }

        return switch (command) {
            case "lib add" -> new AddCommand(lib, args);
            case "lib list" -> new ListCommand(lib, args);
            case "lib size" -> new SizeCommand(lib);
            case "lib search" -> new SearchCommand(lib, args);
            case "lib remove" -> new RemoveCommand(lib, args);

            case "pl create" -> new CreateCommand(lib, args);
            case "pl list" -> new ListPlaylistCommand();
            case "pl add" -> new AddPlaylistCommand(args);
            case "pl show" -> new ShowCommand(args);
            case "pl rmsong" -> new RemoveSongFromPlaylistCommand(args);
            case "pl remove" -> new RemovePlaylistCommand(args);
            case "pl rename" -> new RenameCommand(args);

            case "select" -> new SelectCommand(args);
            case "help" -> new HelpCommand(helpstring);
            case "quit" -> new QuitCommand();
            default -> new InvalidCommand();
        };
    }
}

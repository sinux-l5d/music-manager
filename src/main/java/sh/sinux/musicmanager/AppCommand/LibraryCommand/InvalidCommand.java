package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;

/**
 * @author Simon Leonard
 */
public class InvalidCommand implements Command {
    @Override
    public String execute() {
        return "Invalid command.";
    }
}

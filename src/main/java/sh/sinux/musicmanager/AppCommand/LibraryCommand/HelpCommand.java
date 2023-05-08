package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;

/**
 * @author Simon Leonard
 */

public class HelpCommand implements Command {
    private final String helpstring;
    public HelpCommand(String helpstring) {
        this.helpstring = helpstring;
    }

    @Override
    public String execute() {
        return helpstring;
    }
}

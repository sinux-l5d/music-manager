package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.Musicmanager;

/**
 * @author Simon Leonard
 */
public class QuitCommand implements Command {

    @Override
    public String execute() {
        Musicmanager.stop();
        return "Bye!";
    }
}

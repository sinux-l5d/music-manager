package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.Musicmanager;

public class QuitCommand implements Command {

    @Override
    public String execute() {
        Musicmanager.stop();
        return "Bye!";
    }
}

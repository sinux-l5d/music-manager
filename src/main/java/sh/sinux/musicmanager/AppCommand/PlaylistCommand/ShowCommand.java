package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;

public class ShowCommand implements Command {
    private final String[] args;

    public ShowCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length != 1) return "Invalid number of arguments.";
        return Playlists.playlists.get(Integer.parseInt(args[0]) - 1).show();
    }
}

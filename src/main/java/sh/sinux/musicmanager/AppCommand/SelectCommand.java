package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.Musicmanager;

public class SelectCommand implements Command {
    private final String[] args;

    public SelectCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length != 1) return "Invalid arguments.";
        var index = Integer.parseInt(args[0]);

        if (index < 0 || index > SearchCommand.getLastSearch().length) return "Invalid index.";
        if (index == 0) {
            Musicmanager.select(null);
            return "Selection cleared.";
        }

        var song = SearchCommand.getLastSearch()[index - 1];
        Musicmanager.select(song);
        return "Selected: " + song.toString();
    }
}

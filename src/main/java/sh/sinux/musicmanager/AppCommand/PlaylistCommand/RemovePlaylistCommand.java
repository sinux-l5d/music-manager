package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;

/**
 * @author Simon Leonard
 */
public class RemovePlaylistCommand implements Command {
    private final String[] args;

    public RemovePlaylistCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length != 1) return "Invalid number of arguments.";
        int id;
        try {
            id = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            return "Invalid playlist id.";
        }
        Playlists.playlists.remove(id);
        return "Removed playlist.";
    }
}

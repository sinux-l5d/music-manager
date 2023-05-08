package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;

import java.util.Arrays;

/**
 * @author Simon Leonard
 */
public class RenameCommand implements Command {
    private final String[] args;

    public RenameCommand(String[] args) {
        // Concat args[1:]
        if (args.length > 2) {
            var newArgs = new String[2];
            newArgs[0] = args[0];
            newArgs[1] = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            this.args = newArgs;
        } else {
            this.args = args;
        }
    }

    @Override
    public String execute() {
        if (args.length != 2) return "Invalid number of arguments.";
        try {
            var playlistId = Integer.parseInt(args[0]) - 1;
            var playlist = Playlists.playlists.get(playlistId);
            playlist.setName(args[1]);
            return "Playlist renamed.";
        } catch (NumberFormatException e) {
            return "Invalid playlist id.";
        } catch (NullPointerException e) {
            return "Playlist not found.";
        } catch (Exception e) {
            return "Unknown error.";
        }
    }
}

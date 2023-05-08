package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.Playlist.Playlist;

/**
 * @author Simon Leonard
 */
public class RemoveSongFromPlaylistCommand implements Command {
    private final Playlist playlist;
    private final int id;
    public RemoveSongFromPlaylistCommand(String[] args) {
        if (args.length == 2) {
            this.playlist = Playlists.playlists.get(Integer.parseInt(args[0]) - 1);
            this.id = Integer.parseInt(args[1]) - 1;
        } else {
            this.playlist = null;
            this.id = -1;
        }
    }

    @Override
    public String execute() {
        if (playlist == null) return "Invalid playlist id.";
        if (id == -1) return "Invalid song id.";

        var song = playlist.get(id);
        if (song == null) return "Invalid song id.";

        playlist.remove(song);
        return "Removed \"" + song.getTitle() + "\" from playlist \"" + playlist.getName() + "\".";
    }
}

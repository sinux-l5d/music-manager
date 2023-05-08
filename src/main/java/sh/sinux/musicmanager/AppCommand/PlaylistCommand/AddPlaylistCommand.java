package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.Musicmanager;
import sh.sinux.musicmanager.Playlist.Playlist;

/**
 * @author Simon Leonard
 */
public class AddPlaylistCommand implements Command {
    private final Playlist playlist;

    public AddPlaylistCommand(String[] args) {
        if (args.length == 1)
            this.playlist = Playlists.playlists.get(Integer.parseInt(args[0]) - 1);
        else
            this.playlist = null;
    }

    @Override
    public String execute() {
        if (playlist == null) return "Invalid playlist id.";
        var song = Musicmanager.getSelected();
        if (song == null) return "No song selected, use lib search first.";
        playlist.add(song);
        return "Added \"" + song.getTitle() + "\" to playlist \"" + playlist.getName() + "\".";
    }
}

package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Playlist.Playlist;

/**
 * @author Simon Leonard
 */
public class CreateCommand implements Command {
    private final LibraryStorage lib;
    private final String name;

    public CreateCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
        this.name = String.join(" ", args);
    }

    @Override
    public String execute() {
        if (name.isEmpty()) return "Playlist name cannot be empty.";
        var pl = new Playlist(lib, name);
        Playlists.playlists.put(pl.getId(), pl);
        return "Playlist created \"" + pl.getName() + "\" with id " + (pl.getId() + 1) + ".";
    }
}

package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Song.Song;
import sh.sinux.musicmanager.Song.comparators.*;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class ListCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;

    public ListCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length > 1) return "Invalid number of arguments.";
        if (args.length == 0) return lib.toString();
        Comparator<Song> comparator = switch (args[0]) {
            case "title" -> new NaturalOrderComparator();
            case "artist" -> new ArtistComparator();
            case "album" -> new AlbumComparator();
            case "genre" -> new GenreComparator();
            default -> new TrackNumberComparator();
        };
        return lib.toString(comparator);
    }
}

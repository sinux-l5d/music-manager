package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Song.Song;

import java.util.Arrays;

public class SearchCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;
    public SearchCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length < 2) return "Invalid arguments.";
        var searchType = args[0];
        var searchQuery = String.join(" ", args).substring(searchType.length() + 1);

        Song[] res = switch (searchType) {
            case "title" -> lib.searchByTitle(searchQuery);
            case "artist" -> lib.searchByArtist(searchQuery);
            case "album" -> lib.searchByAlbum(searchQuery);
            default -> new Song[0];
        };

        return Arrays.stream(res).map(Song::toString).reduce((a, b) -> a + "\n" + b).orElse("No results found.");
    }
}

package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Song.Song;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;

    private static Song[] lastSearch = new Song[0];

    public SearchCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
        this.args = args;
    }

    public static Song[] getLastSearch() {
        return lastSearch;
    }

    @Override
    public String execute() {
        if (args.length < 2) return "Invalid arguments.";
        var searchType = args[0];
        // Joining the rest of the arguments into a single string
        var searchQuery = String.join(" ", args).substring(searchType.length() + 1);

        Song[] res = switch (searchType) {
            case "title" -> lib.searchByTitle(searchQuery);
            case "artist" -> lib.searchByArtist(searchQuery);
            case "album" -> lib.searchByAlbum(searchQuery);
            default -> new Song[0];
        };

        lastSearch = res;

        return IntStream.range(0, res.length)
                .mapToObj(i -> (i + 1) + ". " + res[i].toString())
                .collect(Collectors.joining("\n", "", ""));
    }
}

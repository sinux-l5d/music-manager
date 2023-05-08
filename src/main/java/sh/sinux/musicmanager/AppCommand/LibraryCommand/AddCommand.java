package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Song.Song;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;

    public AddCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;

        String regex = "\"(?<title>[^\"]+)\"\\s+\"(?<artist>[^\"]+)\"\\s+\"(?<album>[^\"]+)\"\\s+\"(?<genre>[^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.join(" ", args));

        if (matcher.matches()) {
            args = new String[4];
            args[0] = matcher.group("title");
            args[1] = matcher.group("artist");
            args[2] = matcher.group("album");
            args[3] = matcher.group("genre");
        } else {
            args = new String[0];
        }

        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length != 4) return "Invalid number of arguments.";
        var song = new Song(args[0], args[1], args[2], args[3]);
        lib.add(song);
        return "Added " + song.getTitle() + " (id: " + song.getTrackNumber() + ") to library.";
    }
}

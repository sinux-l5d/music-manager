package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.Song.Song;

public class AddCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;

    public AddCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
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

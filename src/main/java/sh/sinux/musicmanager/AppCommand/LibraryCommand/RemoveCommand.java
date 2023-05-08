package sh.sinux.musicmanager.AppCommand.LibraryCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Simon Leonard
 */
public class RemoveCommand implements Command {
    private final LibraryStorage lib;
    private final String title;
    private final String artist;
    public RemoveCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;

        String regex = "\"(?<title>[^\"]+)\"\\s+\"(?<artist>[^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.join(" ", args));

        if (matcher.matches()) {
            title = matcher.group("title");
            artist = matcher.group("artist");
        } else {
            title = null;
            artist = null;
        }
    }

    @Override
    public String execute() {
        if (title == null || artist == null)
            return "Invalid arguments. Usage: lib remove \"<title>\" \"<artist>\"";

        Arrays.stream(lib.searchByArtist(artist)).filter(song -> song.getTitle().equals(title)).forEach(lib::remove);
        return "Removed \"" + title + "\" by \"" + artist + "\"";
    }
}

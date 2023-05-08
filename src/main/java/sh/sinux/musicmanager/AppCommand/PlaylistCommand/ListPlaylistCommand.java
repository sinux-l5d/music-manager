package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.AppCommand.Command;
import sh.sinux.musicmanager.Playlist.Playlist;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ListPlaylistCommand implements Command {

    @Override
    public String execute() {
        return Arrays.stream(Playlists.playlists.values())
                .map(Playlist::toString)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining("\n"),
                        result -> result.isBlank() ? "No playlists." : result)
                );
    }
}

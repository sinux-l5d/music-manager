package sh.sinux.musicmanager.AppCommand.PlaylistCommand;

import sh.sinux.musicmanager.MyHashMap.MyHashMap;
import sh.sinux.musicmanager.Playlist.Playlist;

/**
 * @author Simon Leonard
 */
public class Playlists {
    protected static MyHashMap<Integer, Playlist> playlists = new MyHashMap<>(Integer.class, Playlist.class);

}

package sh.sinux.musicmanager.Playlist;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.MyQueue.MyQueue;
import sh.sinux.musicmanager.Song.Song;

public class Playlist {
    private final LibraryStorage libraryStorage;
    private MyQueue<Integer> playlist;
    private String name;

    public Playlist(LibraryStorage libraryStorage, String name) {
        this.libraryStorage = libraryStorage;
        this.name = name;
        playlist = new MyQueue<>();
    }

    /**
     * Check if a track exists in the library
     * @param trackNumber The track number to check
     * @return True if the track exists, false otherwise
     */
    private boolean exists(int trackNumber) {
        return libraryStorage.get(trackNumber) != null;
    }

    /**
     * Add a track to the end of the playlist
     * @param song The song to add
     */
    public void add(Song song) {
        if (song == null) return;
        add(song.getTrackNumber());
    }

    /**
     * Add a track to the end of the playlist
     * @param trackNumber The track number to add
     */
    public void add(int trackNumber) {
        if (!exists(trackNumber)) return;
        playlist.enqueue(trackNumber);
    }

    /**
     * Remove a track from anywhere in the playlist
     * @param song The song to remove
     */
    public void remove(Song song) {
        if (song == null) return;
        remove(song.getTrackNumber());
    }

    /**
     * Remove a track from anywhere in the playlist
     * @param trackNumber The track number to remove
     */
    public void remove(int trackNumber) {
        var temp = new MyQueue<Integer>();
        // We have to dequeue all the tracks and enqueue them into a temp queue,
        // except the track we want to remove.
        // With a stack, we could pop tracks until we find the one we want to remove,
        // then stack back the tracks we popped.
        while (!playlist.isEmpty()) {
            var track = playlist.dequeue();
            if (track != trackNumber) {
                temp.enqueue(track);
            }
        }
        playlist = temp;
    }

    /**
     * Rename the playlist
     * @param name New name of the playlist
     */
    public void setName(String name) {
        this.name = name;
    }

}

package sh.sinux.musicmanager.Playlist;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.MyQueue.MyQueue;
import sh.sinux.musicmanager.Song.Song;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Playlist {
    private static int NEXT_ID = 0;
    private final LibraryStorage libraryStorage;
    private MyQueue<Integer> playlist;
    private int id;
    private String name;

    public Playlist(LibraryStorage libraryStorage, String name) {
        this.libraryStorage = libraryStorage;
        this.name = name;
        this.id = NEXT_ID++;
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
     * @param song The song to add. Track number will be used.
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String show() {
        // get all Ids from the queue
        var ids = new ArrayList<Integer>();
        var first = playlist.peek();
        do {
            var id = playlist.dequeue();
            ids.add(id);
            playlist.enqueue(id);
        } while (!Objects.equals(playlist.peek(), first));

        // print all songs in the playlist
        return "Playlist \"" + name + "\"\n" + IntStream.range(0, ids.size())
                .mapToObj(i -> (i + 1) + ". " + libraryStorage.get(ids.get(i)).toString())
                .collect(Collectors.joining("\n"));
    }

    public String toString() {
        return (id + 1) + ". " + name + " (" + playlist.size() + " songs)";
    }
}

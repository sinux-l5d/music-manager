package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.MyHashMap.MyHashMap;
import sh.sinux.musicmanager.Song.Song;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LibraryStorageHashMap implements LibraryStorage {
    private final MyHashMap<Integer, Song> libraryStorage;

    public LibraryStorageHashMap(){
        libraryStorage = new MyHashMap<>(Integer.class, Song.class);
    }

    @Override
    public void add(Song song) {
        libraryStorage.put(song.getTrackNumber(), song);
    }

    /**
     * Removes the song from the library storage
     *
     * @param song
     */
    @Override
    public void remove(Song song) {
        remove(song.getTrackNumber());
    }

    @Override
    public void remove(int trackNumber) {
        libraryStorage.remove(trackNumber);
    }

    /**
     * Updates the song in the library storage
     * The track number is used to find the song to update
     *
     * @param song
     */
    @Override
    public void update(Song song) {
        libraryStorage.put(song.getTrackNumber(), song);
    }

    /**
     * Returns the song with the given track number
     * @param trackNumber The track number of the song to return
     * @return The song with the given track number or null if no song is found
     */
    @Override
    public Song get(int trackNumber) {
        return libraryStorage.get(trackNumber);
    }

    /**
     * Returns songs with the given title
     * String.contains() is used to find songs with the given title
     * @param title The complete or partial title of the song to return
     * @return An array of songs with the given title or an empty array if no songs are found
     */
    @Override
    public Song[] searchByTitle(String title) {
        return search((song) -> song.getTitle().contains(title));
    }

    @Override
    public Song[] searchByAlbum(String album) {
        return search((song) -> song.getAlbum().contains(album));
    }

    @Override
    public Song[] searchByArtist(String artist) {
        return search((song) -> song.getArtist().contains(artist));
    }

    /**
     * Interface for lambda expression used in search methods
     */
    private interface SearchLambda {
        boolean run(Song song);
    }

    /**
     * Generic search method
     * @param match lambda expression that takes a song and the query, and returns true if the song matches the query
     * @return
     */
    private Song[] search(SearchLambda match) {
        var songs = new Song[libraryStorage.size()];
        int i = 0;
        for (Song song : libraryStorage.values()) {
            if (match.run(song)) {
                songs[i] = song;
                i++;
            }
        }
        // Trim the array to the correct size
        var trimmedSongs = new Song[i];
        System.arraycopy(songs, 0, trimmedSongs, 0, i);
        return trimmedSongs;
    }
}

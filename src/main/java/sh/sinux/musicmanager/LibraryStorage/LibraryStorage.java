package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public interface LibraryStorage {
    void add(Song song);

    /**
     * Removes the song from the library storage
     * @param song Song to remove
     */
    void remove(Song song);
    /**
     * Removes the song from the library storage
     * @param trackNumber Track number of the song to remove
     */
    void remove(int trackNumber);

    Song get(int trackNumber);

    String toString();

    /**
     * String representation of the library storage sorted by the given comparator
     * Note that it doesn't apply to all library storages, MyHashMap for example can't be sorted
     *
     * @param comparator Comparator to use for sorting
     * @return String representation of the library storage
     */
    String toString(Comparator<Song> comparator);
    int size();

    Song[] searchByTitle(String title);
    Song[] searchByAlbum(String album);
    Song[] searchByArtist(String artist);
}

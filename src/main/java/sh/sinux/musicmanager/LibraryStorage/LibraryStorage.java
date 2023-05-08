package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public interface LibraryStorage {
    /**
     * Adds the song to the library storage
     * @param song Song to add
     */
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

    /**
     * Gets a song from the library storage
     * @param trackNumber Track number of the song to get
     * @return Song with the given track number
     */
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

    /**
     * Gets the size of the library storage
     * @return Size of the library storage
     */
    int size();

    /**
     * Searches for songs with the given title
     * use String.contains() to search
     * @param title Title to search for
     * @return Array of songs with the given title
     */
    Song[] searchByTitle(String title);

    /**
     * Searches for songs with the given album
     * use String.contains() to search
     * @param album Album to search for
     * @return Array of songs with the given album
     */
    Song[] searchByAlbum(String album);

    /**
     * Searches for songs with the given artist
     * use String.contains() to search
     * @param artist Artist to search for
     * @return Array of songs with the given artist
     */
    Song[] searchByArtist(String artist);
}

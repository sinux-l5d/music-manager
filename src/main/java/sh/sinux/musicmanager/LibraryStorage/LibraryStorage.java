package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

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
    int size();

    Song[] searchByTitle(String title);
    Song[] searchByAlbum(String album);
    Song[] searchByArtist(String artist);
}

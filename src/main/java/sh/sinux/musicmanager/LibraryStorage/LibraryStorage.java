package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

public interface LibraryStorage {
    void add(Song song);

    /**
     * Removes the song from the library storage
     * @param song
     */
    void remove(Song song);
    void remove(int trackNumber);

    Song get(int trackNumber);
    Song[] searchByTitle(String title);
    Song[] searchByAlbum(String album);
    Song[] searchByArtist(String artist);
}

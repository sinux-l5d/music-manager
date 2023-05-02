package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

import java.util.Arrays;

public class LibraryStorageCommon implements LibraryStorage {
    private final LibraryStorage linkedListLibraryStorage;
    private final LibraryStorage hashMapLibraryStorage;

    public LibraryStorageCommon(){
        linkedListLibraryStorage = new LibraryStorageLinkedList();
        hashMapLibraryStorage = new LibraryStorageHashMap();
    }

    @Override
    public void add(Song song) {
        linkedListLibraryStorage.add(song);
        hashMapLibraryStorage.add(song);
    }

    /**
     * Removes the song from the library storage
     *
     * @param song
     */
    @Override
    public void remove(Song song) {
        linkedListLibraryStorage.remove(song);
        hashMapLibraryStorage.remove(song);
    }

    @Override
    public void remove(int trackNumber) {
        linkedListLibraryStorage.remove(trackNumber);
        hashMapLibraryStorage.remove(trackNumber);
    }

    @Override
    public Song get(int trackNumber) throws RuntimeException {
        var s1 = linkedListLibraryStorage.get(trackNumber);
        var s2 = hashMapLibraryStorage.get(trackNumber);
        if (s1 == s2) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }

    @Override
    public Song[] searchByTitle(String title) {
        var s1 = linkedListLibraryStorage.searchByTitle(title);
        var s2 = hashMapLibraryStorage.searchByTitle(title);
        if (Arrays.equals(s1, s2)) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }

    @Override
    public Song[] searchByAlbum(String album) {
        var s1 = linkedListLibraryStorage.searchByAlbum(album);
        var s2 = hashMapLibraryStorage.searchByAlbum(album);
        if (Arrays.equals(s1, s2)) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }

    @Override
    public Song[] searchByArtist(String artist) {
        var s1 = linkedListLibraryStorage.searchByArtist(artist);
        var s2 = hashMapLibraryStorage.searchByArtist(artist);
        if (Arrays.equals(s1, s2)) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }
}

package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.Song.Song;

import java.util.Arrays;
import java.util.Comparator;

public class LibraryStorageCommon implements LibraryStorage {
    private final LibraryStorageLinkedList linkedListLibraryStorage;
    private final LibraryStorageHashMap hashMapLibraryStorage;
    private static LibraryStorageCommon instance;

    private LibraryStorageCommon(){
        linkedListLibraryStorage = new LibraryStorageLinkedList();
        hashMapLibraryStorage = new LibraryStorageHashMap();
    }

    public static LibraryStorageCommon getInstance(){
        if (instance == null)
            instance = new LibraryStorageCommon();
        return instance;
    }

    @Override
    public void add(Song song) {
        linkedListLibraryStorage.add(song);
        hashMapLibraryStorage.add(song);
    }

    /**
     * Removes the song from the library storage
     *
     * @param song The song to remove
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
        // TODO: don't use Arrays.equals, what if it's not the same order?
        if (Arrays.equals(s1, s2)) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }

    public int size() throws RuntimeException {
        var s1 = linkedListLibraryStorage.size();
        var s2 = hashMapLibraryStorage.size();
        if (s1 == s2) {
            return s1;
        } else {
            throw new RuntimeException("Inconsistent library storage");
        }
    }

    @Override
    public String toString() {
        return linkedListLibraryStorage.toString();
    }

    @Override
    public String toString(Comparator<Song> comparator) {
        return linkedListLibraryStorage.toString(comparator);
    }
}

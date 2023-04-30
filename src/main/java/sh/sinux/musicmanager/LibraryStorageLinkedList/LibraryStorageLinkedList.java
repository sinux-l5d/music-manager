package sh.sinux.musicmanager.LibraryStorageLinkedList;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;
import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;
import sh.sinux.musicmanager.Song.Song;

public class LibraryStorageLinkedList implements LibraryStorage {
    MyLinkedList<Song> songs = new MyLinkedList<Song>();

    @Override
    public void add(Song song) {
        songs.add(song);
    }

    @Override
    public void remove(Song song) {

    }

    @Override
    public void remove(int trackNumber) {

    }

    @Override
    public void update(Song song) {

    }

    @Override
    public Song get(int trackNumber) {
        return null;
    }

    @Override
    public Song[] searchByTitle(String title) {
        return new Song[0];
    }

    @Override
    public Song[] searchByAlbum(String album) {
        return new Song[0];
    }

    @Override
    public Song[] searchByArtist(String artist) {
        return new Song[0];
    }
}

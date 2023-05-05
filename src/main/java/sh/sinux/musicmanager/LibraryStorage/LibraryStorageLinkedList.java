package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;
import sh.sinux.musicmanager.Song.Song;

import java.util.ArrayList;
import java.util.List;

public class LibraryStorageLinkedList implements LibraryStorage {
    MyLinkedList<Song> songs = new MyLinkedList<>();

    @Override
    public void add(Song song) {
        songs.add(song);
    }

    @Override
    public void remove(Song song) {
        songs.remove(song);
    }

    @Override
    public void remove(int trackNumber) {
        Song toRemove = null;
        for (Song song : songs) {
            if (song.getTrackNumber() == trackNumber) {
                toRemove = song;
                break;
            }
        }
        if (toRemove != null) {
            songs.remove(toRemove);
        }
    }

    @Override
    public Song get(int trackNumber) {
        for (Song song : songs) {
            if (song.getTrackNumber() == trackNumber) {
                return song;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return songs.size();
    }

    @Override
    public Song[] searchByTitle(String title) {
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                matchingSongs.add(song);
            }
        }
        return matchingSongs.toArray(new Song[matchingSongs.size()]);
    }

    @Override
    public Song[] searchByAlbum(String album) {
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                matchingSongs.add(song);
            }
        }
        return matchingSongs.toArray(new Song[matchingSongs.size()]);
    }

    @Override
    public Song[] searchByArtist(String artist) {
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                matchingSongs.add(song);
            }
        }
        return matchingSongs.toArray(new Song[matchingSongs.size()]);
    }

}

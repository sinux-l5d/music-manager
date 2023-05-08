package sh.sinux.musicmanager.test;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorageCommon;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorageLinkedList;
import sh.sinux.musicmanager.Song.Song;

public class testSearchAlgoActualTiming {
    public static void main(String[] args) {
        Song[] songs = new Song[]{
                new Song("Title 1", "Album 1", "Artist 1", "genre 1"),
                new Song("Title 1", "Album 2", "Artist 2", "genre 1"),
                new Song("Title 1", "Album 3", "Artist 3", "genre 1"),
                new Song("Title 1", "Album 4", "Artist 4", "genre 1"),
                new Song("Title 2", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 3", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 4", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 5", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 6", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 7", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 8", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 9", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 10", "Album 5", "Artist 2", "genre 1"),

        };
        LibraryStorageLinkedList storage = new LibraryStorageLinkedList();
        for (Song song : songs) {
            storage.add(song);
        }
        System.out.println(storage.toString());
        var startTime = System.nanoTime();
        var matchedSongs = storage.searchByTitle("Title 1");
        var endTime = System.nanoTime();
        System.out.println("Songs found: " + matchedSongs.length);
        System.out.println("Time taken: " + (endTime - startTime) + "ns");
    }
}

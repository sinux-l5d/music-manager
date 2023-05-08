package sh.sinux.musicmanager.test;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorageCommon;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorageLinkedList;
import sh.sinux.musicmanager.Song.Song;
import sh.sinux.musicmanager.Song.comparators.AlbumComparator;
import sh.sinux.musicmanager.Song.comparators.ArtistComparator;

public class testMergeAlgoActualTiming {
    public static void main(String[] args) {
        Song[] songs = new Song[]{
                new Song("Title 1", "Album 1", "Artist 1", "genre 1"),
                new Song("Title 1", "Album 2", "Artist 2", "genre 1"),
                new Song("Title 1", "Album 3", "Artist 3", "genre 1"),
                new Song("Title 1", "Album 5", "Artist 4", "genre 1"),
                new Song("Title 1", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 3", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 4", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 5", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 6", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 7", "Album 5", "Artist 2", "genre 1"),
                new Song("Title 8", "Album 4", "Artist 5", "genre 1"),
                new Song("Title 9", "Album 4", "Artist 2", "genre 1"),
                new Song("Title 10", "Album 5", "Artist 2", "genre 1"),

        };
        LibraryStorageLinkedList storage = new LibraryStorageLinkedList();
        for (Song song : songs) {
            storage.add(song);
        }
        ArtistComparator artistComparator = new ArtistComparator();

        var startTimeMerge = System.nanoTime();
        storage.mergeSort(artistComparator, 0, storage.size()-1);
        var endTimeMerge = System.nanoTime();

        var startTimeSelection = System.nanoTime();
        storage.selectionSort(artistComparator);
        var endTimeSelection = System.nanoTime();

        System.out.println("Time taken (merge sort): " + (endTimeMerge - startTimeMerge) + "ns");
        System.out.println("Time taken (selection sort): " + (endTimeSelection - startTimeSelection) + "ns");
    }
}

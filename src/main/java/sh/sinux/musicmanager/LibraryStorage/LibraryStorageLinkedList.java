package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;
import sh.sinux.musicmanager.Song.Song;
import sh.sinux.musicmanager.Song.comparators.AlbumComparator;
import sh.sinux.musicmanager.Song.comparators.GenreComparator;
import sh.sinux.musicmanager.Song.comparators.RatingComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LibraryStorageLinkedList implements LibraryStorage {
    MyLinkedList<Song> songs = new MyLinkedList<>();

    /**
     * adds a song to the library
     * @param song the song to add
     */
    @Override
    public void add(Song song) {
        songs.add(song);
    }

    /**
     * removes a song from the library
     * @param song the song to remove
     */
    @Override
    public void remove(Song song) {
        songs.remove(song);
    }

    /**
     * removes a song from the library by track number
     * @param trackNumber the track number of the song to remove
     */
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
    public void update(Song song) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * gets a song from the library by track number
     * @param trackNumber the track number of the song to get
     * @return the song with the given track number, or null if no such song exists
     */
    @Override
    public Song get(int trackNumber) {
        for (Song song : songs) {
            if (song.getTrackNumber() == trackNumber) {
                return song;
            }
        }
        return null;
    }

    /**
     * get songs from the library by title
     * @param title the title of the songs to get
     * @return an array of songs with the given title
     */
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

    /**
     * get songs from the library by album
     * @param album the title of the songs to get
     * @return an array of songs with the given album
     */
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
    /**
     * get songs from the library by artist
     * @param artist the title of the songs to get
     * @return an array of songs with the given artist
     */
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
    /**
     * Sorts the songs using a custom comparator (using selection sort)
     *
     * @param comparator The comparator to use for sorting
     */
    public void selectionSort(Comparator<Song> comparator) {
        int size = songs.size();
        // Iterate through each element of the list
        for (int i = 0; i < size - 1; i++) {
            // Find the smallest element in the unsorted portion of the list
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                Song currentSong = songs.get(j);
                //smallestSong which has the smallest Unicode value
                Song smallestSong = songs.get(minIndex);
                //using the comparator to compare the current song with the smallest song
                if (comparator.compare(songs.get(j), songs.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // swap the smallest element with the first element of the unsorted portion of the list ( smallest to the front)
            if (minIndex != i) {
                Song temp = songs.get(i);
                songs.set(i, songs.get(minIndex));
                songs.set(minIndex, temp);
            }
        }
    }

    public static void main(String[] args) {
        LibraryStorageLinkedList library = new LibraryStorageLinkedList();

        // Add some songs to the library
        library.add(new Song("Song A", "Album 2", "Artist 1", "Pop"));
        library.add(new Song("Song B", "Album 1", "Artist 1", "Pop"));
        library.add(new Song("Song D", "Album 1", "Artist 3", "Jazz1"));
        library.add(new Song("Song D", "Album 1", "Artist 3", "Jazz1"));
        library.add(new Song("Song c", "Album 3", "Artist 1", "Jazz1"));
        library.add(new Song("Song D", "Album 3", "Artist 1", "Jazz4"));

        // Display unsorted library
        System.out.println("Unsorted library:");
        displayLibrary(library);

        // Sort the library using the AlbumComparator
        Comparator<Song> albumComparator = new AlbumComparator();
        Comparator<Song> ratingComparator = new RatingComparator();
        Comparator<Song> genre = new GenreComparator();

        library.selectionSort(albumComparator);
        System.out.println("Sorted library:(album)");
        displayLibrary(library);
        library.selectionSort(genre);
        System.out.println("Sorted library:(genre)");
        displayLibrary(library);

    }

    private static void displayLibrary(LibraryStorageLinkedList library) {
        for (int i = 0; i < library.songs.size(); i++) {
            Song song = library.songs.get(i);
            System.out.printf("%d. %s - %s - %s - %s\n", i + 1, song.getTitle(), song.getAlbum(), song.getArtist(), song.getGenre());
        }
    }

}

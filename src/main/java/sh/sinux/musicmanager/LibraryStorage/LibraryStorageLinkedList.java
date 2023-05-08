package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;
import sh.sinux.musicmanager.Song.Song;
import sh.sinux.musicmanager.Song.comparators.TrackNumberComparator;

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

    @Override
    public int size() {
        return songs.size();
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
            if (song.getTitle().contains(title)) {
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
            if (song.getAlbum().contains(album)) {
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
            if (song.getArtist().contains(artist)) {
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
                if (comparator.compare(currentSong, smallestSong) < 0) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Song song : songs) {
            sb.append(song.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Sorts the songs using a custom comparator (using selection sort)
     * Note that it sort in place, so the original list will be modified
     * After, it puts the list back to its trackNumber order
     *
     * @param comparator The comparator to use for sorting
     * @return the string representation of the sorted list
     */
    public String toString(Comparator<Song> comparator) {
        selectionSort(comparator);

        var res = toString();

        if (!(comparator instanceof TrackNumberComparator))
            selectionSort(new TrackNumberComparator());

        return res;
    }
    public static void main(String[] args) {
        Song[] songs = new Song[]{
                new Song("Bohemian Rhapsody", "A Night at the Opera", "Queen", "rock"),
                new Song("Like a Rolling Stone", "Highway 61 Revisited", "Bob Dylan", "rock"),
                new Song("Shape of You", "÷ (Divide)", "Ed Sheeran", "pop"),
                new Song("Bad Guy", "When We All Fall Asleep, Where Do We Go?", "Billie Eilish", "pop"),
                new Song("Uptown Funk", "Uptown Special", "Mark Ronson ft. Bruno Mars", "funk"),
                new Song("Le vent nous portera", "Des visages des figures", "Noir Désir", "rock"),
                new Song("La Bohème", "La Bohème", "Charles Aznavour", "chanson"),
                new Song("Je t'aime... moi non plus", "Jane Birkin - Serge Gainsbourg", "Serge Gainsbourg & Jane Birkin", "chanson"),
                new Song("Alors on danse", "Cheese", "Stromae", "electro"),
                new Song("Formidable", "Racine Carrée", "Stromae", "pop"),
        };
        LibraryStorageCommon storage = LibraryStorageCommon.getInstance();
        for (Song song : songs) {
            storage.add(song);
        }
        System.out.println(storage.toString());
        var startTime = System.nanoTime();

        var endTime = System.nanoTime();

        System.out.println("Time taken: " + (endTime - startTime) + "ns");
    }
}

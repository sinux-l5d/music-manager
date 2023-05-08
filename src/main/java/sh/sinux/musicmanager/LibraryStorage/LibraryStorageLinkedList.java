package sh.sinux.musicmanager.LibraryStorage;

import sh.sinux.musicmanager.MyLinkedList.MyLinkedList;
import sh.sinux.musicmanager.Song.Song;
import sh.sinux.musicmanager.Song.comparators.TrackNumberComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Simon Leonard
 * @author Zi Jie Lee
 */
public class LibraryStorageLinkedList implements LibraryStorage {
    private final MyLinkedList<Song> songs;
    public LibraryStorageLinkedList() {
        songs = new MyLinkedList<>();
    }
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

    /**
     * gets the size of the library
     * @return the size of the library
     */
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
     * @param album the album of the songs to get
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
     * @param artist the artist of the songs to get
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
    /**
     * Merge sort algorithm to sort a list of Song objects.
     * It takes a Comparator<Song> object to define the custom sorting order for the Song objects
     * and recursively sorts the list using a divide and conquer approach.
     *
     * @param comparator The Comparator<Song> object to define the custom sorting order.
     * @param leftPos The left position of the sublist to sort.
     * @param rightPos The right position of the sublist to sort.
     */
    private void mergeSort(Comparator<Song> comparator, int leftPos, int rightPos) {

        if (leftPos < rightPos) {
            int middlePos = (leftPos + rightPos) / 2;
            // This 2 lines of code is to divide the list into 2 parts until the list is divided into single elements
            mergeSort(comparator, leftPos, middlePos);
            mergeSort(comparator, middlePos + 1, rightPos);

            // Merge the sorted sublists back into the original list
            merge(comparator, leftPos, middlePos, rightPos);
        }
    }
    /**
     * Merging two sorted sublists back into the original list (one level back).
     * It uses the provided Comparator<Song> object to compare the Song objects and determine their order.
     *
     * @param comparator The Comparator<Song> object to define the custom sorting order.
     * @param left The left position of the sublist to merge.
     * @param middle The middle position of the sublist to merge.
     * @param right The right position of the sublist to merge.
     *
     */
    private void merge(Comparator<Song> comparator, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        // Fill temp arrays with the data from their side of the section
        Song[] leftArray = new Song[leftSize];
        Song[] rightArray = new Song[rightSize];
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = songs.get(left + i);
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = songs.get(middle + 1 + i);
        }
        // Track where we are in temp data
        int leftIndex = 0;
        int rightIndex = 0;
        // Track where we are inserting into in main array
        int mergedIndex = left;
        while (leftIndex < leftSize && rightIndex < rightSize) {
            // Compare the two objects using the comparator
            if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                songs.set(mergedIndex, leftArray[leftIndex]);
                leftIndex++;
            } else {
                songs.set(mergedIndex, rightArray[rightIndex]);
                rightIndex++;
            }
            mergedIndex++;
        }
        //checking if there is any element left
        while (leftIndex < leftSize) {
            songs.set(mergedIndex, leftArray[leftIndex]);
            leftIndex++;
            mergedIndex++;
        }
        while (rightIndex < rightSize) {
            songs.set(mergedIndex, rightArray[rightIndex]);
            rightIndex++;
            mergedIndex++;
        }
    }

    /**
     * Return a string formatted that contains all the songs in the library
     *
     */
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
        mergeSort(comparator, 0, songs.size() - 1);

        var res = toString();

        if (!(comparator instanceof TrackNumberComparator))
            mergeSort(new TrackNumberComparator(), 0, songs.size() - 1);

        return res;
    }

}

package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

public class RatingComparator implements Comparator<Song> {
    /**
     * Compares two songs by Rating
     */
    @Override
    public int compare(Song song1, Song song2) {
        return Float.compare(song1.getRating(), song2.getRating());
    }
}

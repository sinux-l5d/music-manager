package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class NaturalOrderComparator implements Comparator<Song> {
    /**
     * Compares two songs by their natural order (by title and artist)
     */
    @Override
    public int compare(Song song1, Song song2) {
        return song1.compareTo(song2);
    }
}

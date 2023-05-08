package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class GenreComparator implements Comparator<Song> {
    /**
     * Compares two songs by genre, and where two genres are the same, use natural order (by title and artist)
     */
    @Override
    public int compare(Song song1, Song song2) {
        if (song1.getGenre().equals(song2.getGenre()))
            return song1.compareTo(song2);
        return song1.getGenre().compareTo(song2.getGenre());
    }
}

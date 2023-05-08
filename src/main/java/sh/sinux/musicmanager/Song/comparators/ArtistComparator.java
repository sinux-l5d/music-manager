package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class ArtistComparator implements Comparator<Song> {
    /**
     * Compares two songs by artist, and where two artists are the same, use natural order (by title and artist)
     */
    @Override
    public int compare(Song song1, Song song2) {
        if (song1.getArtist().equals(song2.getArtist()))
            return song1.compareTo(song2);
        return song1.getArtist().compareTo(song2.getArtist());
    }
}

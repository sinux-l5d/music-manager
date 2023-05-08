package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class TrackNumberComparator implements Comparator<Song> {
    /**
     * Compares two songs by their track number
     */
    @Override
    public int compare(Song song1, Song song2) {
        return Integer.compare(song1.getTrackNumber(), song2.getTrackNumber());
    }
}

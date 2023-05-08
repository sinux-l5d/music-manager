package sh.sinux.musicmanager.Song.comparators;

import sh.sinux.musicmanager.Song.Song;

import java.util.Comparator;

/**
 * @author Simon Leonard
 */
public class AlbumComparator implements Comparator<Song> {
    /**
     * Compares two songs by album, and where two albums are the same, use natural order (by title and artist)
     */
    @Override
    public int compare(Song song1, Song song2) {
        if (song1.getAlbum().equals(song2.getAlbum()))
            return song1.compareTo(song2);
        return song1.getAlbum().compareTo(song2.getAlbum());
    }
}

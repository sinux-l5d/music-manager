package sh.sinux.musicmanager.Song;

public class Song implements Comparable<Song> {
    private static int NEXT_TRACK_NUMBER = 0;
    private int trackNumber;
    private String title;
    private String artist;
    private String album;
    private String genre;
    /**
     * Play count is the number of times the song has been played
     */
    private int playCount;
    /**
     * Rating is a float between 0.0 and 5.0
     */
    private float rating;

    /// Constructors ///

    /**
     * Public constructor with essential parameters
     * trackNumber is automatically generated
     * playCount and rating are set to 0
     */
    public Song(String title, String album, String artist, String genre) {
        this(NEXT_TRACK_NUMBER++, title, album, artist, genre, 0, 0.0f);
    }

    /**
     * Private constructor with all parameters
     */
    private Song(int trackNumber, String title, String album, String artist, String genre, int playCount, float rating) {
        this.trackNumber = trackNumber;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.playCount = playCount;
        this.rating = rating;
    }

    /// Methods ///

    /**
     * Fake method to simulate playing a song
     * Increments playCount by 1
     */
    public void incrementPlayCount() {
        playCount++;
    }

    /**
     * The natural order for Songs is by title (ASCENDING), and where two titles are the same, by artist (ASCENDING).
     * @param other the other song to compare to
     * @return -1 if this song is less than the other song, 0 if they are equal, 1 if this song is greater than the other song
     */
    @Override
    public int compareTo(Song other) {
        if (title.equals(other.title))
            return artist.compareTo(other.artist);
        return title.compareTo(other.title);
    }

    @Override
    public int hashCode() {
        // Unkown behaviour if we change the values of a song after it has been added to a hashmap
        return title.hashCode() + artist.hashCode() + album.hashCode() + genre.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof Song))
            return false;
        Song otherSong = (Song) other;
        return title.equals(otherSong.title)
                && artist.equals(otherSong.artist)
                && album.equals(otherSong.album)
                && genre.equals(otherSong.genre);
    }

    public String toString() {
        return String.format("%s - %s", artist, title);
    }

    /// Getters ///

    public int getTrackNumber() { return trackNumber; }

    public String getTitle() { return title; }

    public String getAlbum() { return album; }

    public String getArtist() { return artist; }

    public String getGenre() { return genre; }

    public int getPlayCount() { return playCount; }

    public float getRating() { return rating; }
}

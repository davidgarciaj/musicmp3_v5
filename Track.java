/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    //Cuenta las veces que se ha reproducido
    private int playCount;
    //Indica los minutos que dura la cancion
    private int minuteTrack;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
        playCount = 0;
        minuteTrack = 0;
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
        playCount = 0;
        minuteTrack = 0;
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the playCount.
     * @return The playCount.
     */
    public int getPlayCount()
    {
        return playCount;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
    
    /**
     * Return the minute of the file.
     * @return The minute of the file.
     */
    public int getMinuteTrack()
    {
        return minuteTrack;
    }
    
    /**
     * Modifica los minutos de la canci�n
     */
    public void chageMinuteTrack(int newMinuteTrack){
        minuteTrack = newMinuteTrack;
    }
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")"
                + "- Minutos de duraci�n: " + minuteTrack + " - N� de veces reproducido: " + playCount;
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }
    
    /**
     * Incrementa el contador del n�mero de veces que se ha reproducido
     */
    public void incrementCount(){
        playCount++;
    }
    
    /**
     * Pone a cero el contador del n�mero de veces que se ha reproducido
     */
    public void resetCount(){
        playCount = 0;
    }
}

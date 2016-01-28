import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //Indica si se esta reproduciendo
    private boolean inPlay;
    //Indica que canción se esta reproduciendo
    private int inPlayIndex;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        inPlay = false;
        inPlayIndex = -1;
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if((indexValid(index)) && (inPlay == false)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            inPlay = true;
            inPlayIndex = index;
            track.incrementCount();
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle() + " - Nº de veces reproducido: " + track.getPlayCount());
        }
        else{
            System.out.println("EROR --- Ya hay una reprodución en curso.");
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if((tracks.size() > 0) && (inPlay == false)) {
            player.startPlaying(tracks.get(0).getFilename());
            inPlay = true;
            inPlayIndex = 0;
            tracks.get(0).incrementCount();
        }
        else{
            System.out.println("EROR --- Ya hay una reprodución en curso.");
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        inPlay = false;
        inPlayIndex = -1;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * Muestra la información de la canción del titulo proporcionado
     */
    public void findInTitle(String title){
        boolean notfind = true;
        for(Track track : tracks) {
            if(track.getTitle().contains(title)) {
                System.out.println(track.getDetails());
                notfind = false;
            }
        }
        if(notfind){
                System.out.println("No se ha encontrado ninguna canción con ese titulo.");            
        }
    }
    
    /**
     * Cambia la información sobre los minutos que dura una canción
     */
    public void cambiaMinutosCancion(int index, int minutos){        
            tracks.get(index).chageMinuteTrack(minutos);
    }
    
    /**
     * Indica si se esta reproduciendo una canción y que canción se esta reproduciendo
     */
    public void isPlaying(){
        if(inPlay){
            System.out.println("Actualmente se esta reproduciendo la canción:");
            System.out.println(tracks.get(inPlayIndex).getDetails());
        }
        else{
            System.out.println("Actualmente no se esta reproduciendo ninguna canción.");
        }
    }
    
    /**
     * Show a list of all the tracks in the collection with a iterator.
     */
    public void listAllTracksWithIterator()
    {
        System.out.println("Track listing: ");
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()){
            System.out.println(it.next().getDetails());
        }
        System.out.println();
    }
    
    /**
     * Elimina todas las canciones de un artista indicado
     */
    public void removeByArtist(String artist){
       Iterator<Track> it = tracks.iterator();
        while(it.hasNext()){            
            Track artista = it.next();
            if(artista.getArtist().contains(artist)){
                it.remove();
            }
        } 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


//Import commands

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Sound {
    
       /*
            This class will contain the main elements of the music
            there will be managed the name of the song
            the artist name
            the album name
            the absolute path of the song
            the length of the sound
            the format of the sound
            Its size of the music file
            the year of publication of the sound
            The date when the song was added to the application
            And we will provide sufficient methods to deal with the sound
    
            This class will also provide the user the ability to modify the sound information
            add comments
            add a rating to the sound
    */
    
    
    //Constants representing the supported formats
    
    private final String[] SOUND_FORMAT = {"MP3","WAV","MKV","FLV","OGG"}; 
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private final int MB_SIZE = (int) Math.pow(2,20);
    
    
    
    //Instance variables
    
    private File sound_file;
    private String sound_publisher;
    private String sound_lyrics;
    private String sound_name;
    private String artist_name;
    private String album_name;
    private String absolute_path;
    private String user_comments;
    private int sound_genre;
    private int sound_duration;
    private int sound_format;
    private int sound_size;
    private int sound_year;
    private Date song_add;
    private Mp3File work_file;
    private ID3v2 file_tag;
    
    
    //Static method (for testing purposes )
    
    public static String getAppName(){
        return "carnage";
    }
    
    
    //constructor
    
    public Sound(String sound_file)throws Exception{
        //The path of the song is the main element that we will work on
        //Once we find the path we can freely work on the sound
        this.sound_file = new File(sound_file);
        if(this.sound_file.exists()){
            this.init(sound_file);
        }else{
            throw new Exception("Errors occured while opening the file");
        }
    }
    
    private void init(String sound_file)throws Exception{
        this.absolute_path = this.sound_file.getAbsolutePath();
        this.sound_size = (int) (this.sound_file.length()/MB_SIZE);
        
        //We start using the Mp3 library for this work
        //We will find all the other informations concerning the file
            
        this.work_file = new Mp3File(sound_file);
        
        
        //now we get all the key elements of the MP3 file
        
        if(this.work_file.hasId3v2Tag()){
            
           this.file_tag = this.work_file.getId3v2Tag();
           this.album_name = file_tag.getAlbum();
           this.artist_name = file_tag.getArtist();
           this.sound_duration = file_tag.getLength();
           this.sound_name = file_tag.getTitle();
           this.sound_year = Integer.parseInt(file_tag.getYear());
           this.sound_genre = file_tag.getGenre();
           this.sound_publisher = file_tag.getPublisher();
           this.sound_lyrics = file_tag.getLyrics();
           this.sound_format = 0;
           this.user_comments = file_tag.getComment();
            
        }  
        
    }
    
    //setters

    public void setSound_publisher(String sound_publisher) {
        this.sound_publisher = sound_publisher;
        save_file();
    }

    public void setSound_lyrics(String sound_lyrics) {
        this.sound_lyrics = sound_lyrics;
        save_file();
    }

    public void setSound_name(String sound_name) {
        this.sound_name = sound_name;
        save_file();
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
        save_file();
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
        save_file();
    }

    public void setSound_genre(int sound_genre) {
        this.sound_genre = sound_genre;
        save_file();
    }

    public void setUser_comments(String user_comments) {
        this.user_comments = user_comments;
        save_file();
    }
    
    //This function saves all modifications that could have happend on the file
    //It takes the file_tag and attributes all the setters
    
    private void save_file(){
        try {
            
            

            this.file_tag.setAlbum(this.album_name);
            this.file_tag.setArtist(this.artist_name);
            this.file_tag.setTitle(this.sound_name);
            this.file_tag.setLyrics(this.sound_lyrics);
            this.file_tag.setComment(this.user_comments);
            this.file_tag.setPublisher(this.sound_publisher);
            this.file_tag.setGenre(this.sound_genre);
            this.work_file.save(this.sound_file.getName());
            
            
            
        } catch (IOException | NotSupportedException ex) {
            //will add return text
        }
        
    }
    
    //getters

    public File getSound_file() {
        return sound_file;
    }

    public String getSound_publisher() {
        return sound_publisher;
    }

    public String getSound_lyrics() {
        return sound_lyrics;
    }

    public String getSound_name() {
        return sound_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getAbsolute_path() {
        return absolute_path;
    }

    public int getSound_duration() {
        return sound_duration;
    }

    public String getSound_format() {
        return this.SOUND_FORMAT[this.sound_format];
    }

    public int getSound_size() {
        return sound_size;
    }

    public int getSound_year() {
        return sound_year;
    }

    public Date getSong_add() {
        return song_add;
    }

    public String getUser_comments() {
        return user_comments;
    }
    
    public String getSound_genre(){
        return this.file_tag.getGenreDescription();
    }
    
    
    
}

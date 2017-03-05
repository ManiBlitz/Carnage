/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


//Import commands

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import mp3agic-master.src.main.java.com.mpatric.mp3agic;

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
    private String sound_name;
    private String artist_name;
    private String album_name;
    private String absolute_path;
    private int sound_duration;
    private int sound_format;
    private int sound_size;
    private int sound_year;
    private Date song_add;
    private String user_comments;
    
    
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
            throw new Exception("File not found");
        }
    }
    
    private void init(String sound_file){
        this.absolute_path = this.sound_file.getAbsolutePath();
        this.sound_size = (int) (this.sound_file.length()/MB_SIZE);
        Mp3File work_file = new Mp3File(sound_file);
        
    }
    
}

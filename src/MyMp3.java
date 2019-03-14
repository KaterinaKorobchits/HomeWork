import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyMp3 {
    String artist;
    String album;
    String title;
    long length;
    String path;
    SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");

    public MyMp3(String artist, String album, String title, long length, String path) {
        this.artist = artist;
        this.album = album;
        this.title = title;
        this.length = length;
        this.path = path;
    }

    public boolean equals(MyMp3 obj) {
        if (this == obj)
            return  true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if ( !(artist.equals(obj.artist)))
            return false;
        if ( !(album.equals(obj.album)))
            return false;
        if ( !(title.equals(obj.title)))
            return false;
        return true;
    }

    public String myToString(MyMp3 prev) {
        sf.setTimeZone(TimeZone.getTimeZone("UTC"));
        StringBuilder res = new StringBuilder();
        if(!(prev.artist.equals(artist)) || prev == null)
            res.append(artist + "\n\t" + album + "\n");
        else if (!(prev.album.equals(album)) || prev == null)
            res.append("\t" + album + "\n");
        return res.append("\t\t" + title + "[" + sf.format(new Date(length*1000)) + "]" + " (" + path + ")").toString();
    }
}

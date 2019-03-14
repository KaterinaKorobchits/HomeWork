import com.mpatric.mp3agic.*;
import javafx.beans.binding.StringBinding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    static List<File> files = new ArrayList<>();
    static Set<String> dublicates = new HashSet<>();
    static HashMap<String, List<File>> filesWithHash = new HashMap<>();
    static ArrayList<MyMp3> listMyMp3 = new ArrayList<>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidDataException, UnsupportedTagException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите путь к папке -> ");
        String dirStr = in.nextLine();
        File dir = new File(dirStr);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("\nВведенный путь не является каталогом!");
        } else if (!dir.canRead()) {
            System.out.println("\nКаталог не доступен для чтения!");
        } else {
            long start = System.currentTimeMillis();
            files = (List<File>) FileUtils.listFiles(dir, null, true);
            for (File file : files) {
                if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3"))
                    addToListMyMp3(file, listMyMp3);
                byte[] hash = MessageDigest.getInstance("MD5").digest(FileUtils.readFileToByteArray(file));
                String k = DatatypeConverter.printHexBinary(hash);
                List<File> v = filesWithHash.get(k);
                if (v == null)
                    v = new ArrayList<>();
                else
                    dublicates.add(k);
                v.add(file);
                filesWithHash.put(k, v);
            }
            System.out.println("----------------- Вывод ВСЕХ дубликатов ---------------");
            printDublicates();
            Collections.sort(listMyMp3, new Comparator<MyMp3>() {
                @Override
                public int compare(MyMp3 o1, MyMp3 o2) {
                    int a = o1.artist.compareTo(o2.artist);
                    if (a == 0) {
                        int b = o1.album.compareTo(o2.album);
                        if (b == 0)
                            return o1.title.compareTo(o2.title);
                        else
                            return b;
                    } else
                        return a;
                }
            });
            System.out.println("----------------- Каталогизатор MP3-файлов ------------");
            printMp3Files();
            System.out.println("----------------- Дубликаты MP3-файлов ------------");
            printDublicatesMp3();
        }
    }

    static void printDublicates() {
        int numberDublicate = 1;
        for (String st : dublicates) {
            System.out.println("Дубликаты - " + numberDublicate + ":");
            for (File f : filesWithHash.get(st))
                System.out.println("\t\u23F9 " + f.getAbsolutePath());
            numberDublicate++;
        }
    }

    static void printDublicatesMp3() {
        boolean temp = false;
        for (int i = 1; i < listMyMp3.size(); i++) {
            MyMp3 mp3 = listMyMp3.get(i);
            MyMp3 mp3prev = listMyMp3.get(i - 1);
            if (mp3.equals(mp3prev)) {
               if (!temp) {
                   System.out.println(mp3.artist + ", " + mp3.album + ", " + mp3.title);
                   System.out.println("\t\u23F9 " + mp3.path);
               }
               System.out.println("\t\u23F9 " + mp3prev.path);
               temp = true;
            }
            else
                temp = false;
        }
    }

    static void printMp3Files() {
        for (int i = 1; i < listMyMp3.size(); i++)
            System.out.println(listMyMp3.get(i).myToString(listMyMp3.get(i - 1)));
    }

    static void addToListMyMp3(File mp3, ArrayList<MyMp3> listMyMp3) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File(mp3);
        if (mp3File.hasId3v2Tag()) {
            ID3v2 id3v1Tag = mp3File.getId3v2Tag();
            String artist = id3v1Tag.getArtist();
            if (artist == null)
                artist = "НЕТ ИСПОЛНИТЕЛЯ";
            String album = id3v1Tag.getAlbum();
            if (album == null)
                album = "НЕТ АЛьБОМА";
            String title = id3v1Tag.getTitle();
            if (title == null)
                title = "НЕТ НАЗВАНИЯ";
            long length = mp3File.getLengthInSeconds();
            String path = mp3.getAbsolutePath();
            listMyMp3.add(new MyMp3(artist, album, title, length, path));
        }
    }
}

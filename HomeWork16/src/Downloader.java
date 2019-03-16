import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class Downloader implements Runnable {
    final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss[SS]");
    Exchanger<File> exchanger;
    File file;
    Map<String, File> map;

    public Downloader(Exchanger<File> exchanger, Map<String, File> map) {
        this.exchanger = exchanger;
        this.map = new HashMap<>(map);
    }

    @Override
    public void run() {
        try {
            for(Map.Entry<String, File> pair : map.entrySet()) {
                file = exchanger.exchange(Logic.readThroughURlConnectionToFile(pair.getKey(), pair.getValue()));
                System.out.println("*** файл загружен в " + simpleDateFormat.format(new Date()) + " ***");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

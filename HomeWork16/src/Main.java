import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<File> exchanger = new Exchanger<>();
        Map<String, File> map = new HashMap<>();
        map.put("https://goo.gl/Hc8J4n", new File("file.json"));
        map.put("https://goo.gl/tFpBDV", new File("file.xml"));
        Thread downloader = new Thread(new Downloader(exchanger,map));
        Thread handler = new Thread(new Handler(exchanger, map.size()));
        handler.start();
        downloader.start();
        handler.join();
        System.out.println("*** Parse JSON:");
        for (Person person : Logic.persons)
            System.out.println(person.toString());
        System.out.println("*** Parse XML:");
        System.out.println(Logic.listOfMyClasses.toString());
        //secondPArt();
    }

    static void secondPArt() throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        System.out.println("стартанул побочный поток в " + new Date());
        Thread.sleep(1000);
        System.out.println("уже поспал main");
        t.temp = false;
        t.join();
        System.out.println("завершился побочный поток в " + new Date());
    }
}

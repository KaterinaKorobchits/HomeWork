import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Exchanger;


public class Handler implements Runnable {
    final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss[SS]");
    Exchanger<File> exchanger;
    File file;
    int numberFiles;

    public Handler(Exchanger<File> exchanger, int numberFiles) {
        this.exchanger = exchanger;
        this.numberFiles = numberFiles;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < numberFiles; i++) {
                file = exchanger.exchange(new File("response.txt"));
                System.out.println("*** файл получен для обработки в " + simpleDateFormat.format(new Date()) + "***");
                Logic.handlerFile(file);
                System.out.println("*** файл обработан в " + simpleDateFormat.format(new Date()) + "***");
                System.out.println("=====================================");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

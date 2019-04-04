import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class DownloaderData implements Runnable {

    private String urlAddress;
    private File file;

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (urlAddress != null) {
            try {
                Logic.daysFromUrl = objectMapper.readValue(new URL(urlAddress ), Day[].class);
            } catch (IOException e) {
                System.out.println("*** Неверный формат JSON!(cервер) ***");
            }
        }
        else if (file != null)
            try (FileInputStream reader = new FileInputStream(file)) {
                Logic.daysFromFile = objectMapper.readValue(reader, Day[].class);
            } catch (FileNotFoundException e) {
                System.out.println("*** Файл не найден! ***");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("*** Неверный формат JSON!(файл) ***");
            }
    }

    public static class Builder {
        private DownloaderData downloaderData;

        public Builder() {
            downloaderData = new DownloaderData();
        }

        public Builder fromFile(File file) {
            downloaderData.file = file;
            return this;
        }

        public Builder fromServer(String urlAddress) {
            downloaderData.urlAddress = urlAddress;
            return this;
        }

        public DownloaderData build() {
            return downloaderData;
        }
    }
}

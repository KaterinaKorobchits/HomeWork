import org.codehaus.jackson.map.ObjectMapper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Logic {

    static ArrayList<MyClass> listOfMyClasses = new ArrayList<>();
    static Person[] persons;

    public static File readThroughURlConnectionToFile(String urlAddress, File file) throws IOException {
        URL url = new URL(urlAddress);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             FileWriter write = new FileWriter(file)) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            write.write(stringBuilder.toString());
        }
        return file;
    }

    public static void handlerFile (File file) {
        if (file.getName().endsWith(".json")) {
            try (FileInputStream reader = new FileInputStream(file)) {
                ObjectMapper objectMapper = new ObjectMapper();
                persons = objectMapper.readValue(reader, Person[].class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (file.getName().endsWith(".xml")) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = null;
                parser = factory.newSAXParser();
                MySAXParse handler = new MySAXParse();
                parser.parse(file, handler);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static class MySAXParse extends DefaultHandler {

        MyClass myClassLast;
        MyMethod myMethodLast;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("class") && attributes.getValue("name") != null)
                myClassLast = new MyClass(attributes.getValue("name"));
            if (qName.equals("method") && attributes.getValue("name") != null)
                myMethodLast = new MyMethod(attributes.getValue("name"));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("class"))
                listOfMyClasses.add(myClassLast);
            if (qName.equals("method"))
                myClassLast.addMethod(myMethodLast);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            myMethodLast.setValue(new String(ch, start, length));
        }

    }
}

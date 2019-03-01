import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Wife.class, new WifeDeAndSerializer()).create();
        try (FileInputStream reader = new FileInputStream("note1.txt")) {
            readJSONThroughURlConnectionToFile("https://goo.gl/Hc8J4n", "note1.txt");
            Person[] persons = objectMapper.readValue(reader, Person[].class);
            System.out.println("*** Parse with JACKSON:");
            for (Person person : persons)
                System.out.println(person.toString());
            String json = gson.toJson(persons);
            System.out.println("*** Stream with GSON:");
            System.out.println(json);
            Person[] persons2 = gson.fromJson(json, Person[].class);
            System.out.println("*** Parse with GSON:");
            for (Person person: persons2)
                System.out.println(person.toString());
            System.out.println("*** Stream with JSONObject/JSONArray:");
            JSONArray jsonArray = new JSONArray();
            for(int i = 0; i < persons2.length; i++) {
                Person personi = persons2[i];
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", personi.name);
                jsonObject.put("age", personi.age);
                jsonObject.put("isStudent", personi.isStudent);
                if(personi.pet != null) {
                    ArrayList<String> pets = personi.pet;
                    JSONArray jsonArrayPet = new JSONArray();
                    for (int k = 0; k < pets.size(); k++)
                        jsonArrayPet.put(pets.get(k));
                    jsonObject.put("pet", jsonArrayPet);
                }
                if(personi.wife != null) {
                    Wife wife = personi.wife;
                    JSONObject jsonObjectWife = new JSONObject();
                    jsonObjectWife.put("name", wife.name);
                    jsonObjectWife.put("age", wife.age);
                    jsonObject.put("wife", jsonObjectWife);
                }
                jsonArray.put(jsonObject.);
            }
            System.out.println(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJSONThroughURlConnectionToFile(String urlAddress, String fileName) throws IOException {
        URL url = new URL(urlAddress);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             FileWriter write = new FileWriter(fileName)) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            write.write(stringBuilder.toString());
        }
    }
}

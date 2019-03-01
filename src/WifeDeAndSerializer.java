import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ListIterator;

public class WifeDeAndSerializer implements JsonSerializer<Wife>, JsonDeserializer<Wife> {

    @Override
    public Wife deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonNull())
            return null;
        else {
            String[] list = jsonElement.getAsString().split(" ");
            return new Wife(list[0],Integer.parseInt(list[1]));
        }
    }

    @Override
    public JsonElement serialize(Wife wife, Type type, JsonSerializationContext jsonSerializationContext) {
        if(wife == null)
            return null;
        else
            return new JsonPrimitive(wife.name + " " + wife.age);
    }
}

package it.mbkj.lib.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class StringNotNullAdapter implements JsonSerializer<String>, JsonDeserializer<String> {
    public StringNotNullAdapter() {
    }

    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("null")) {
                return "";
            }
        } catch (Exception var6) {
        }

        try {
            return json.getAsString();
        } catch (NumberFormatException var5) {
            throw new JsonSyntaxException(var5);
        }
    }

    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}


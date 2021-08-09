package it.mbkj.lib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GSonUtil {
    public static Gson gson;

    private GSonUtil() {
    }

    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }

        return gsonString;
    }

    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        Object t = null;

        try {
            if (gson != null) {
                t = gson.fromJson(gsonString, cls);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return (T) t;
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        ArrayList list = new ArrayList();

        try {
            Gson gson = new Gson();
            JsonArray arry = (new JsonParser()).parse(json).getAsJsonArray();
            Iterator var5 = arry.iterator();

            while(var5.hasNext()) {
                JsonElement jsonElement = (JsonElement)var5.next();
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return list;
    }

    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = (List)gson.fromJson(gsonString, (new TypeToken<List<Map<String, T>>>() {
            }).getType());
        }

        return list;
    }

    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = (Map)gson.fromJson(gsonString, (new TypeToken<Map<String, T>>() {
            }).getType());
        }

        return map;
    }

    public static <T> String mapToJson(Map<String, T> map) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(map);
        }

        return jsonStr;
    }

    static {
        gson = (new GsonBuilder()).setLenient().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter()).registerTypeAdapter(Integer.TYPE, new IntegerDefault0Adapter()).registerTypeAdapter(Double.class, new DoubleDefault0Adapter()).registerTypeAdapter(Double.TYPE, new DoubleDefault0Adapter()).registerTypeAdapter(Long.class, new LongDefault0Adapter()).registerTypeAdapter(Long.TYPE, new LongDefault0Adapter()).registerTypeAdapter(String.class, new StringNotNullAdapter()).create();
    }
}

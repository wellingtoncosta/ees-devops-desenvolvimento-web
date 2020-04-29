package br.uece.eesdevop.bancodedados.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;

public enum GsonUtil {

    INSTANCE;

    private Gson gson;

    GsonUtil() {
        gson = new GsonBuilder().create();
    }

    public <T> T parse(Reader reader, Class<T> clazz) {
        return gson.fromJson(reader, clazz);
    }

    public String stringify(Object object) {
        return gson.toJson(object);
    }

}

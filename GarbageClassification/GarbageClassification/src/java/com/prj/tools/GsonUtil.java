package com.prj.tools;

import com.google.gson.Gson;

public class GsonUtil {
    public static <T> T jsonToObject(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }
}

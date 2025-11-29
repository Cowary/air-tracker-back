package org.cowary.arttrackerback.integration.util;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ApiUrl {
    private static StringBuilder urlBuilder;
    private final Map<String, String> query = new TreeMap<>();

//    public ApiUrl(String fileName) {
//        properUtil = new ProperUtil(fileName);
//        urlBuilder = new StringBuilder(
//                properUtil.getProp("BASE_URL")
//        );
//    }

    public ApiUrl(String baseUrl) {
        urlBuilder = new StringBuilder(baseUrl);
    }

    public ApiUrl addQuery(String key, String value) {
        if (query.containsKey(key)) {
            throw new IllegalStateException("ключ " + key + " уже добавлен");
        }

        if (key.isBlank()) throw new IllegalStateException("ключ не может быть пустым");
        if (value.isBlank()) throw new IllegalStateException("значение не может быть пустым");

        query.put(key, value);
        return this;
    }

    public ApiUrl addQuery(String name, int value) {
        return addQuery(name, Integer.toString(value));
    }

    public ApiUrl appendPath(String path) {
        urlBuilder.append("/")
                .append(path);
        return this;
    }

    public ApiUrl appendPath(int path) {
        return appendPath(Integer.toString(path));
    }

    public ApiUrl appendPathFromFile(String keyFile) {
        return appendPath(keyFile);
    }

    public URL build() {
        try {
            if (query.size() > 0) {
                List<String> keys = new ArrayList<>(query.keySet());
                for (int i = 0; i < keys.size(); i++) {
                    urlBuilder.append(i == 0 ? "?" : "&");
                    String paramName = keys.get(i);
                    urlBuilder.append(paramName)
                            .append("=")
                            .append(query.get(paramName));
                }
            }
            return new URL(urlBuilder.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

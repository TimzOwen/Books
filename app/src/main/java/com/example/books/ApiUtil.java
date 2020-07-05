package com.example.books;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ApiUtil {
    private ApiUtil() {}

    public static final String BASE_API_KEY =
            "https://www.googleapis.com/books/v1/volumes";

    public static URL buildUrl(String title) {
        String fullUrl = BASE_API_KEY + "?q=" + title;
        URL url = null;
        try {
            url = new URL(fullUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }
}

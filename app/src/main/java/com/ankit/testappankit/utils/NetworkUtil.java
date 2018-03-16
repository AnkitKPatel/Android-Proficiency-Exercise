package com.ankit.testappankit.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Network util.
 */
public class NetworkUtil {

    /**
     * Is connected to internet boolean.
     *
     * @return the boolean
     */
    public static boolean isConnectedToInternet() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.toString();
        }
        return success;
    }
}
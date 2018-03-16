package com.ankit.testappankit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ANKITKP on 2018-03-16.
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
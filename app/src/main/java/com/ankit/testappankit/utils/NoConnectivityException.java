package com.ankit.testappankit.utils;

import java.io.IOException;

/**
 * Created by ANKITKP on 2018-03-16.
 */
public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}

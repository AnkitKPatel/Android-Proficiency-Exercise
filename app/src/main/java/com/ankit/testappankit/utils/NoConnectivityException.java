package com.ankit.testappankit.utils;

import java.io.IOException;

/**
 * The type No connectivity exception.
 */
public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}

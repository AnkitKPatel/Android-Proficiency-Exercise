package com.ankit.testappankit.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The type Connectivity interceptor.
 */
public class ConnectivityInterceptor implements Interceptor {

    /**
     * Instantiates a new Connectivity interceptor.
     */
    public ConnectivityInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isConnectedToInternet()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}

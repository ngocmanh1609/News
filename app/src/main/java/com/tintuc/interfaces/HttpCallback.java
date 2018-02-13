package com.tintuc.interfaces;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by Hp on 2/6/2018.
 */

public interface HttpCallback {
    void onSuccess(String s);

    void onStart();

    void onFailure(Request request, IOException e);
}

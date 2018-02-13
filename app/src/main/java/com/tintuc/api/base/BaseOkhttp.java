package com.tintuc.api.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.tintuc.interfaces.HttpCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Hp on 2/6/2018.
 */

public class BaseOkhttp implements Callback{

    private static OkHttpClient okHttpClient = null;

    private static HttpLoggingInterceptor logging;

    public static OkHttpClient getOkHttpClient() {

        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient
                                .Builder()
                                .readTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .addInterceptor(logging).build();
        }

        return okHttpClient;
    }

    private boolean wantDialogCancelable = true;
    private boolean wantShowDialog = true;
    private Context context;
    private ProgressDialog progress;
    private String title = "";
    private String message = "Message...";
    private HttpCallback httpCallback;

    protected BaseOkhttp() {

        logging = new HttpLoggingInterceptor();
    }

    public void start() {

        if (this.context != null) {
            progress = new ProgressDialog(this.context);
            progress.setCancelable(wantDialogCancelable);
            progress.setMessage(message);

            if (null != title && TextUtils.isEmpty(title)) {
                progress.setTitle(title);
            }

            if (progress != null && wantShowDialog) {
                progress.show();
            }

            if (httpCallback != null) {
                httpCallback.onStart();
            }
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {

        dissmissDialog();
        if (httpCallback != null) {
            httpCallback.onFailure(request, e);
        }

    }

    @Override
    public void onResponse(Response response) throws IOException {

        dissmissDialog();

        if (response.isSuccessful()) {
            if (httpCallback != null) {
                String s = response.body().string();
                httpCallback.onSuccess(s);
            }
        } else {
            onFailure(null, null);
        }

    }

    private void dissmissDialog() {
        if (progress != null && progress.isShowing()) {
            try {
                if (context instanceof Activity) {
                    if (((Activity)context).isFinishing()) {
                        return;
                    }
                }
                progress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        BaseOkhttp.okHttpClient = okHttpClient;
    }

    public boolean isWantDialogCancelable() {
        return wantDialogCancelable;
    }

    public void setWantDialogCancelable(boolean wantDialogCancelable) {
        this.wantDialogCancelable = wantDialogCancelable;
    }

    public boolean isWantShowDialog() {
        return wantShowDialog;
    }

    public void setWantShowDialog(boolean wantShowDialog) {
        this.wantShowDialog = wantShowDialog;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ProgressDialog getProgress() {
        return progress;
    }

    public void setProgress(ProgressDialog progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpCallback getHttpCallback() {
        return httpCallback;
    }

    public void setHttpCallback(HttpCallback httpCallback) {
        this.httpCallback = httpCallback;
    }

    public static class Builder {


        private BaseOkhttp baseOkhttp = new BaseOkhttp(){};

        /**
         * co muon show dialog khong ?
         * @param wantShowDialog dieu kien
         * @return return this
         */
        public Builder setWantShowDialog(boolean wantShowDialog) {
            baseOkhttp.wantShowDialog = wantShowDialog;
            return this;
        }

        /**
         * cai dat delegate
         * @param httpCallback delegate
         * @return return this
         */
        public Builder setHttpCallback(HttpCallback httpCallback) {
            this.baseOkhttp.httpCallback = httpCallback;
            return this;
        }

        public Builder setContext(Context ctx) {
            baseOkhttp.context = ctx;
            return Builder.this;
        }

        public Builder setWantDialogCancelable(boolean wantDialogCancelable) {
            baseOkhttp.wantDialogCancelable = wantDialogCancelable;
            return Builder.this;
        }

        /**
         * Set title
         * @param title title
         * @return return this
         */
        public Builder setTitle(String title) {
            baseOkhttp.title = title;
            return Builder.this;
        }

        /**
         * Set message
         * @param message message
         * @return return this
         */
        public Builder setMessage(String message) {
            baseOkhttp.message = message;
            return Builder.this;
        }

        public BaseOkhttp build() {
            baseOkhttp.start();
            return baseOkhttp;
        }
    }
}

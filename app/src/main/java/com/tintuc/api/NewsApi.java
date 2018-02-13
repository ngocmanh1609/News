package com.tintuc.api;

import android.content.Context;

import com.tintuc.api.base.BaseOkhttp;
import com.tintuc.interfaces.HttpCallback;
import com.tintuc.util.Define;
import com.tintuc.util.LogUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Hp on 2/6/2018.
 */

public class NewsApi {

    public static void apiEX(Context ctx, HttpCallback httpCallback) {

        BaseOkhttp baseOkhttp = new BaseOkhttp.Builder().setHttpCallback(httpCallback).setContext(ctx)
                                    .setWantShowDialog(true).setWantDialogCancelable(true)
                                    .setMessage("Loading...").setTitle("...").build();
        OkHttpClient okHttpClient = BaseOkhttp.getOkHttpClient();

        Request request = new Request.Builder().url(Define.API_EXAMPLE).build();

        okHttpClient.newCall(request).enqueue(baseOkhttp);
    }

    public static void getListPost(Context ctx, int categoryId, int limit, int offset, HttpCallback httpCallback) {

        BaseOkhttp baseOkhttp = new BaseOkhttp.Builder().setHttpCallback(httpCallback).setContext(ctx)
                .setWantShowDialog(true).setWantDialogCancelable(true)
                .setMessage("Loading...").setTitle("...").build();
        OkHttpClient okHttpClient = BaseOkhttp.getOkHttpClient();

        String url = Define.API_GET_LIST_POST + "?category_id=" + categoryId + "&limit=" + limit + "&offset=" + offset;

        LogUtil.d("getListPost", url);
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(baseOkhttp);
    }

    public static void getPostDetail(Context ctx, int postId, HttpCallback httpCallback) {

        BaseOkhttp baseOkhttp = new BaseOkhttp.Builder().setHttpCallback(httpCallback).setContext(ctx)
                .setWantShowDialog(true).setWantDialogCancelable(true)
                .setMessage("Loading...").setTitle("...").build();
        OkHttpClient okHttpClient = BaseOkhttp.getOkHttpClient();

        String url = Define.API_GET_POST_DETAIL + "?post_id=" + postId;

        LogUtil.d("getPostDetail", url);

        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(baseOkhttp);
    }

}

package com.example.tangdh.tdmusic.webhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by tangdh on 18-3-6.
 */

public class GetMusicUrl {
    public static void sendOkHttpRequest(String adress,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(adress)
                .build();
        client.newCall(request).enqueue(callback);
    }
}

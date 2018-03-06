package com.example.tangdh.tdmusic.webhttp;

/**
 * Created by tangdh on 18-3-6.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}

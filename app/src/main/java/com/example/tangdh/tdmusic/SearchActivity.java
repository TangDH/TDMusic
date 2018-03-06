package com.example.tangdh.tdmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.tangdh.tdmusic.entity.Music;
import com.example.tangdh.tdmusic.webhttp.GetMusicUrl;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by tangdh on 18-2-27.
 */

public class SearchActivity extends AppCompatActivity{
    private Music music;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("SearchActivity","int iut");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchView mSearchView = (SearchView) findViewById(R.id.SearchMusic);
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String newText) {
                GetMusicUrl.sendOkHttpRequest("http://172.20.18.177:8080/GetMusicBySinger?singer="+newText, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("onQuery","wrong");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        String jsonData = response.body().string();
                        Log.d("aa",jsonData);
                        music = gson.fromJson(jsonData,Music.class);
                        Log.d("onResponse",music.getUrl());
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(SearchActivity.this, MusicActivity.class);
                                    intent.putExtra("MusicUrl", music.getUrl());
                                    startActivity(intent);
                                }
                            });
                        }catch (Exception e){
                            throw e;
                        }
                        //Intent intent = new Intent(SearchActivity.this,MusicActivity.class);
                        //intent.putExtra("MusicUrl",music.getUrl());
                        //startActivity(intent);
                        //GotoMusicPlayer( music);

                    }


                });
                return true;
            }
        });

    }

    private void GotoMusicPlayer(final Music music) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent("TDMusic.action");
                intent.addCategory("com.example.TDMusic.category");
                intent.putExtra("MusicUrl",music.getUrl());
                startActivity(intent);
            }
        });
    }
}

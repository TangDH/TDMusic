package com.example.tangdh.tdmusic;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by tangdh on 18-3-3.
 */

public class Navigation_component extends RelativeLayout {
    public Navigation_component(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_navigationbar,this);
        ImageButton SearchButton = (ImageButton) findViewById(R.id.navigation_music);
        SearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SearchActivity.class);
                context.startActivity(intent);
            }
        });
    }
}


package com.example.sahar.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayFeeds extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_feeds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
       // mRecyclerView.setHasFixedSize(true);
        Intent i = getIntent();
        String link = i.getStringExtra("feedLink");
        String feed = i.getStringExtra("feed");
        mTitle.setText(feed);

        FetchRss fetchRss = new FetchRss(this, mRecyclerView);
        fetchRss.execute(link);

    }

}

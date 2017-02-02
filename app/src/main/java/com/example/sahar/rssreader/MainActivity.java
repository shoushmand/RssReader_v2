package com.example.sahar.rssreader;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    final String wsj = "Wall Street Journal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
      TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mListView = (ListView) findViewById(R.id.feed_list);
        String[] feedNames = {"U.s. News", "World News", "U.S. Business", "Opinion", "Markets News", "New York News", "U.S. Technology"};
        final ArrayList<String> feed_Names = new ArrayList<>();
        final Map<String, String> links = new HashMap<>();
        links.put(feedNames[0], "http://www.wsj.com/xml/rss/3_8068.xml");
        links.put(feedNames[1], "http://www.wsj.com/xml/rss/3_7085.xml");
        links.put(feedNames[2], "http://www.wsj.com/xml/rss/3_7014.xml");
        links.put(feedNames[3], "http://www.wsj.com/xml/rss/3_7041.xml");
        links.put(feedNames[4], "http://www.wsj.com/xml/rss/3_7031.xml");
        links.put(feedNames[5],  "http://www.wsj.com/xml/rss/3_7731.xml");
        links.put(feedNames[6],  "http://www.wsj.com/xml/rss/3_7455.xml");


        for(int i = 0; i < feedNames.length; i++){
            feed_Names.add(feedNames[i]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, feed_Names);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String feed = ((TextView) view).getText().toString();
                Intent intent = new Intent(getApplicationContext(), DisplayFeeds.class);
                String feedLink = links.get(feed);
                intent.putExtra("feedLink", feedLink);
                intent.putExtra("feed", feed);
                startActivity(intent);





            }
        });

    }
}

package com.example.sahar.rssreader;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class StoryActivity extends AppCompatActivity implements StoryFragment.OnFragmentInteractionListener {
    TextView title;
    TextView description;
    TextView pubDate;
    ImageView content;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Bundle data = getIntent().getExtras();
        RssItem item = data.getParcelable("item");
        StoryFragment storyFragment = StoryFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().add(R.id.container, storyFragment).commit();















    /*    title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        pubDate = (TextView)findViewById(R.id.date);
        content = (ImageView)findViewById(R.id.image);
        LinearLayout rl = (LinearLayout) findViewById(R.id.parent);
        Bundle data = getIntent().getExtras();
        RssItem item = data.getParcelable("item");
        title.setText(item.getTitle());
        if(item.getContent() == null){
            content.setVisibility(View.GONE);
            rl.removeView(content);
        }else{
            Picasso.with(StoryActivity.this).load(item.getContent()).into(content);
        }
        description.setText(item.getDescription());
        String[] date = item.getPubDate().split(" ");
        String[] time = date[4].split(":");
        String newDate = date[1]+ " "+ date[2]+ ", " + time[0] + ":" + time[1];
        pubDate.setText(newDate);*/








    }


    @Override
    public void onFragmentInteraction(String title) {
        Toast.makeText(this, title, Toast.LENGTH_LONG).show();

    }
}

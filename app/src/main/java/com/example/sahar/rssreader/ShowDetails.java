package com.example.sahar.rssreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ShowDetails extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView pubDate;
    ImageView content;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details);
        title = (TextView)findViewById(R.id.title);
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
        }
        description.setText(item.getDescription());
        String[] date = item.getPubDate().split(" ");
        String[] time = date[4].split(":");
        String newDate = date[1]+ " "+ date[2]+ ", " + time[0] + ":" + time[1];
        pubDate.setText(newDate);
        Picasso.with(ShowDetails.this).load(item.getContent()).into(content);







    }

}

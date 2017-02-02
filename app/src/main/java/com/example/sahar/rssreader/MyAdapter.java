package com.example.sahar.rssreader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Created by sahar on 1/31/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context ctx;
    ArrayList<RssItem> items = new ArrayList<>();
    RssItem item;

    public MyAdapter(Context context, ArrayList<RssItem> items){
        ctx = context;
        this.items = items;

    }



    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        TextView pubDate;
        ImageView content;
        RssItem i;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.title);

            description = (TextView)v.findViewById(R.id.description);
            pubDate = (TextView)v.findViewById(R.id.date);
            content = (ImageView)v.findViewById(R.id.image);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ShowDetails.class);

                    intent.putExtra("item", i);
                    v.getContext().startActivity(intent);

                }
            });

        }



    }




    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.rss_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        String[] date = item.getPubDate().split(" ");
        String[] time = date[4].split(":");
        String newDate = date[1]+ " "+ date[2]+ ", " + time[0] + ":" + time[1];
        holder.pubDate.setText(newDate);
        holder.i = item;
        Picasso.with(ctx).load(item.getContent()).into(holder.content);




    }

    @Override
    public int getItemCount() {

        return items != null ? items.size(): 0;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

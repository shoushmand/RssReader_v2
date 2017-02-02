package com.example.sahar.rssreader;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sahar on 1/31/2017.
 */
public class RssItem implements Parcelable {
    private String title;
    private String description;
    private String pubDate;
    private String content;
    private String link;
    public RssItem(){

    }
    public RssItem(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.title = data[0];
        this.description = data[1];
        this.pubDate = data[2];
        this.content = data[3];
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.title,
                this.description,
                this.pubDate, this.content});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public RssItem createFromParcel(Parcel in) {
            return new RssItem(in);
        }

        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

}

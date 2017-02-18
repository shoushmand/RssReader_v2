package com.example.sahar.rssreader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.w3c.dom.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sahar on 1/31/2017.
 */
public class FetchRss extends AsyncTask<String,Void,Void> {
    Context ctx;
  //  ProgressDialog pd;
    String rssFeedAddress;
    URL url;
    ArrayList<RssItem> rssItems;
    RecyclerView mRecyclerView;
    public FetchRss(Context context, RecyclerView rc){
        ctx = context;
        mRecyclerView = rc;
     //   pd = new ProgressDialog(ctx);
     //   pd.setMessage("Loading...");
    }
    @Override
    protected void onPreExecute() {
       // pd.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(String... params) {
        rssFeedAddress = params[0];
        try {
            url = new URL(rssFeedAddress);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            InputStream inputStream = httpConnection.getInputStream();
            Document document = getDocument(inputStream);
            if(document != null){
                parseXML(document);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseXML(Document document) {

        document.getDocumentElement().normalize();
        rssItems = new ArrayList<>();
        NodeList items = document.getElementsByTagName("item");

        for(int i = 0; i < items.getLength(); i++){
            RssItem rssItem = new RssItem();
            NodeList children = items.item(i).getChildNodes();
            for(int j = 0; j < children.getLength();j++){
                    Node node = children.item(j);
                    if(node.getNodeName().equalsIgnoreCase("title")){
                        rssItem.setTitle(node.getTextContent());
                    }else if(node.getNodeName().equalsIgnoreCase("description")){
                        rssItem.setDescription(node.getTextContent());

                    }else if(node.getNodeName().equalsIgnoreCase("pubDate")){
                        rssItem.setPubDate(node.getTextContent());

                    }
                    else if(node.getNodeName().equalsIgnoreCase("media:content")){

                        String url = node.getAttributes().item(1).getTextContent();
                        rssItem.setContent(url);

                    }
                    else if(node.getNodeName().equalsIgnoreCase("link")){
                         rssItem.setLink(node.getTextContent());

                    }
            }
            rssItems.add(rssItem);


        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
     //   pd.dismiss();
      //  Log.d("rss", String.valueOf(rssItems.size()));
        MyAdapter myAdapter = new MyAdapter(ctx, rssItems);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        mRecyclerView.addItemDecoration(new Decoration(25));
        mRecyclerView.setAdapter(myAdapter);

    }

    private Document getDocument(InputStream inputStream) {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            document = db.parse(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;


        }
        return document;
    }


}


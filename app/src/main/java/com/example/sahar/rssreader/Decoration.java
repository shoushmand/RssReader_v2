package com.example.sahar.rssreader;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sahar on 2/01/2017.
 */
public class Decoration extends RecyclerView.ItemDecoration {
    int space;

    public Decoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.left = space/2;
        outRect.right = space/2;
        if(parent.getChildLayoutPosition(view) == 0){
            outRect.top = space;
        }
    }
}

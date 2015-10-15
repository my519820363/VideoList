package com.volokh.danylo.videolist.adapter.items;

import android.view.View;
import android.view.ViewGroup;

import com.volokh.danylo.videolist.adapter.holders.VideoViewHolder;

public interface VideoItem {
    View createView(ViewGroup parent, int screenWidth);

    void update(int position, VideoViewHolder view);
}

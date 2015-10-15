package com.volokh.danylo.videolist.adapter.items;

import android.app.Activity;

import java.io.IOException;

public class ItemFactory {

    public static BaseVideoItem createItemFromAsset(String assetName, int imageResource, Activity activity) throws IOException {
        return new LocalVideoItem(assetName, activity.getAssets().openFd(assetName), imageResource);
    }
}

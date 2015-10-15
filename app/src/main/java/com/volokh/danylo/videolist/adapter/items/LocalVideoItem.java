package com.volokh.danylo.videolist.adapter.items;

import android.content.res.AssetFileDescriptor;
import android.view.View;

import com.volokh.danylo.videolist.adapter.holders.VideoViewHolder;

public class LocalVideoItem extends BaseVideoItem {

    private final String mTitle;

    private final int mImageResource;

    private final AssetFileDescriptor mAssetFileDescriptor;

    public LocalVideoItem(String title, AssetFileDescriptor assetFileDescriptor, int imageResource) {
        super();
        mTitle = title;
        mAssetFileDescriptor = assetFileDescriptor;
        mImageResource = imageResource;
    }

    @Override
    public void update(int position, final VideoViewHolder viewHolder) {
        viewHolder.mPlayer.setupAsset(mAssetFileDescriptor);
        viewHolder.mTitle.setText(mTitle);
        viewHolder.mCover.setVisibility(View.VISIBLE);

        if (mImageResource > 0) {
            viewHolder.mCover.setImageResource(mImageResource);
        }
    }

    @Override
    public String toString() {
        return getClass() + ", mTitle[" + mTitle + "]";
    }
}

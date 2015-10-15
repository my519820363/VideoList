package com.volokh.danylo.videolist.player;

import android.net.Uri;

import com.volokh.danylo.videolist.adapter.interfaces.VideoPlayerCallback;
import com.volokh.danylo.videolist.ui.VideoPlayerView;

import java.io.File;

public class SetPathDataSourceMessage extends SetDataSourceMessage {

    private final Uri mVideoPathUri;

    public SetPathDataSourceMessage(VideoPlayerView videoPlayerView, String videoPath, VideoPlayerCallback callback) {
        super(videoPlayerView, callback);
        mVideoPathUri = Uri.fromFile(new File(videoPath));
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.setDataSource(mVideoPathUri.toString());
    }
}

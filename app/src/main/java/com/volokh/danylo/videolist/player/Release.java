package com.volokh.danylo.videolist.player;

import com.volokh.danylo.videolist.adapter.interfaces.VideoPlayerCallback;
import com.volokh.danylo.videolist.ui.VideoPlayerView;

public class Release extends PlayerMessage {

    public Release(VideoPlayerView videoPlayerView, VideoPlayerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.release();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.RELEASING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.RELEASED;
    }
}

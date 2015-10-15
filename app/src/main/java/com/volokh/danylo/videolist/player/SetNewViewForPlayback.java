package com.volokh.danylo.videolist.player;

import com.volokh.danylo.videolist.adapter.interfaces.VideoPlayerCallback;
import com.volokh.danylo.videolist.ui.VideoPlayerView;

public class SetNewViewForPlayback extends PlayerMessage {

    private final VideoPlayerView mCurrentPlayer;
    private final VideoPlayerCallback mCallback;

    public SetNewViewForPlayback(VideoPlayerView videoPlayerView, VideoPlayerCallback callback) {
        super(videoPlayerView, callback);
        mCurrentPlayer = videoPlayerView;
        mCallback = callback;
    }

    @Override
    public String toString() {
        return SetNewViewForPlayback.class.getSimpleName() + ", mCurrentPlayer " + mCurrentPlayer;
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.SETTING_NEW_PLAYER;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.IDLE;
    }
}

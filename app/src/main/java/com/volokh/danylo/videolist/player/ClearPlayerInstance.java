package com.volokh.danylo.videolist.player;

import com.volokh.danylo.videolist.adapter.interfaces.VideoPlayerCallback;
import com.volokh.danylo.videolist.ui.VideoPlayerView;

public class ClearPlayerInstance extends PlayerMessage {

    public ClearPlayerInstance(VideoPlayerView videoPlayerView, VideoPlayerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.clearPlayerInstance();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.CLEARING_PLAYER_INSTANCE;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.PLAYER_INSTANCE_CLEARED;
    }
}

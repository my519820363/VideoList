package com.volokh.danylo.videolist.adapter.interfaces;

import com.volokh.danylo.videolist.player.PlayerMessageState;
import com.volokh.danylo.videolist.ui.VideoPlayerView;

public interface VideoPlayerCallback {

    void setVideoPlayerState(VideoPlayerView videoPlayerView, PlayerMessageState playerMessageState);

    PlayerMessageState getCurrentPlayerState();
}

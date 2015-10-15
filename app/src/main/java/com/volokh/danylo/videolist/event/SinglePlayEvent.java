package com.volokh.danylo.videolist.event;

/**
 * Created by yunfeng on 15/10/15.
 */
public class SinglePlayEvent {
    public long playKey;

    public SinglePlayEvent(long playKey) {
        this.playKey = playKey;
    }
}

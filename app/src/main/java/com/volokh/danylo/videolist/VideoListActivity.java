package com.volokh.danylo.videolist;

import android.app.Activity;
import android.os.Bundle;

public class VideoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        addRecyclerView();
    }

    private void addRecyclerView() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoRecyclerViewFragment())
                .commit();
    }

    private void addListView() {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoListFragment())
                .commit();
    }
}

package com.volokh.danylo.videolist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.volokh.danylo.videolist.adapter.VideoListViewAdapter;
import com.volokh.danylo.videolist.adapter.items.ItemFactory;
import com.volokh.danylo.videolist.adapter.items.VideoItem;

import java.io.IOException;
import java.util.ArrayList;

public class VideoListFragment extends Fragment {

    private static final boolean SHOW_LOGS = Config.SHOW_LOGS;
    private static final String TAG = VideoListFragment.class.getSimpleName();

    private final ArrayList<VideoItem> mList = new ArrayList<>();
    private ListView mListView;
    private VideoListViewAdapter mVideoListViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));

            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science2, getActivity()));
            mList.add(ItemFactory.createItemFromAsset("dangyimalaiqiaomen.mp4", R.drawable.rocket_science1, getActivity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        View rootView = inflater.inflate(R.layout.fragment_video_list_view, container, false);

        mListView = (ListView) rootView.findViewById(R.id.list_view);
        mVideoListViewAdapter = new VideoListViewAdapter(getActivity(), mList);
        mListView.setAdapter(mVideoListViewAdapter);
        mVideoListViewAdapter.notifyDataSetChanged();

        return rootView;
    }
}
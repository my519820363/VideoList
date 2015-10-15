package com.volokh.danylo.videolist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.volokh.danylo.videolist.adapter.VideoRecyclerViewAdapter;
import com.volokh.danylo.videolist.adapter.items.ItemFactory;
import com.volokh.danylo.videolist.adapter.items.VideoItem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by danylo.volokh on 9/20/2015.
 */
public class VideoRecyclerViewFragment extends Fragment {

    private static final boolean SHOW_LOGS = Config.SHOW_LOGS;
    private static final String TAG = VideoRecyclerViewFragment.class.getSimpleName();

    private final ArrayList<VideoItem> mList = new ArrayList<>();

    private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
    private RecyclerView mRecyclerView;
    private VideoRecyclerViewAdapter mVideoRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;

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

        View rootView = inflater.inflate(R.layout.fragment_video_recycler_view, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int firstPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                    int lastPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                    int index = lastPosition - firstPosition;
//                    if (index < mRecyclerView.getChildCount()) {
//                        RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(index));
//                        if (viewHolder instanceof VideoViewHolder) {
//                            VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
//                            long singlePlayKey = videoViewHolder.mPlayer.getSinglePlayKey();
//                            EventBus.getDefault().post(new SinglePlayEvent(singlePlayKey));
//                        }
//                    }
//                }
//            }
//        });

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVideoRecyclerViewAdapter = new VideoRecyclerViewAdapter(getActivity(), mList);

        mRecyclerView.setAdapter(mVideoRecyclerViewAdapter);

        mVideoRecyclerViewAdapter.notifyDataSetChanged();

        return rootView;
    }
}
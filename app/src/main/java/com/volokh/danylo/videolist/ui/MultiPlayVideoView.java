package com.volokh.danylo.videolist.ui;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.util.AttributeSet;

import com.volokh.danylo.videolist.adapter.interfaces.VideoPlayerCallback;
import com.volokh.danylo.videolist.player.ClearPlayerInstance;
import com.volokh.danylo.videolist.player.CreateNewPlayerInstance;
import com.volokh.danylo.videolist.player.PlayerHandlerThread;
import com.volokh.danylo.videolist.player.PlayerMessageState;
import com.volokh.danylo.videolist.player.Prepare;
import com.volokh.danylo.videolist.player.Release;
import com.volokh.danylo.videolist.player.Reset;
import com.volokh.danylo.videolist.player.SetAssetsDataSourceMessage;
import com.volokh.danylo.videolist.player.SetNewViewForPlayback;
import com.volokh.danylo.videolist.player.SetPathDataSourceMessage;
import com.volokh.danylo.videolist.player.SetUrlDataSourceMessage;
import com.volokh.danylo.videolist.player.Start;
import com.volokh.danylo.videolist.player.Stop;

import java.util.Arrays;

/**
 * Created by yunfeng on 15/10/15.
 */
public class MultiPlayVideoView extends VideoPlayerView implements VideoPlayerCallback {
    private static final String TAG = MultiPlayVideoView.class.getSimpleName();

    private Uri mUri;

    private AssetFileDescriptor mAssetFileDescriptor;

    private final PlayerHandlerThread mPlayerHandler = new PlayerHandlerThread();

    private PlayerMessageState mCurrentPlayerState = PlayerMessageState.IDLE;

    public MultiPlayVideoView(Context context) {
        super(context);
    }

    public MultiPlayVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiPlayVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MultiPlayVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setVideoPlayerState(VideoPlayerView videoPlayerView, PlayerMessageState playerMessageState) {
        mCurrentPlayerState = playerMessageState;
    }

    @Override
    public PlayerMessageState getCurrentPlayerState() {
        return mCurrentPlayerState;
    }

    public void setupUri(Uri uri) {
        mUri = uri;
        mAssetFileDescriptor = null;
    }

    public void setupAsset(AssetFileDescriptor assetFileDescriptor) {
        mUri = null;
        mAssetFileDescriptor = assetFileDescriptor;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startImpl();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopImpl();
    }

    private void startImpl() {
        if (mUri != null) {
            if (mCurrentPlayerState == PlayerMessageState.STARTING || mCurrentPlayerState == PlayerMessageState.STARTED) {
                if (mUri.toString().equals(getUri().toString())) {
                    mPlayerHandler.resumeQueueProcessing(TAG);
                    return;
                }
            }

            stopImpl();

            mPlayerHandler.pauseQueueProcessing(TAG);
            mPlayerHandler.clearAllPendingMessages(TAG);
            mPlayerHandler.addMessage(new SetNewViewForPlayback(this, this));
            if ("file".equals(mUri.getScheme())) {
                mPlayerHandler.addMessages(Arrays.asList(
                        new CreateNewPlayerInstance(this, this),
                        new SetPathDataSourceMessage(this, mUri.toString(), this),
                        new Prepare(this, this),
                        new Start(this, this)
                ));
            } else if ("http".equals(mUri.getScheme())) {
                mPlayerHandler.addMessages(Arrays.asList(
                        new CreateNewPlayerInstance(this, this),
                        new SetUrlDataSourceMessage(this, mUri.toString(), this),
                        new Prepare(this, this),
                        new Start(this, this)
                ));
            }
            mPlayerHandler.resumeQueueProcessing(TAG);
        } else if (mAssetFileDescriptor != null) {
            if (mCurrentPlayerState == PlayerMessageState.STARTING || mCurrentPlayerState == PlayerMessageState.STARTED) {
                if (mAssetFileDescriptor == getAssetFileDescriptorDataSource()) {
                    mPlayerHandler.resumeQueueProcessing(TAG);
                    return;
                }
            }

            stopImpl();

            mPlayerHandler.pauseQueueProcessing(TAG);
            mPlayerHandler.clearAllPendingMessages(TAG);
            mPlayerHandler.addMessage(new SetNewViewForPlayback(this, this));
            mPlayerHandler.addMessages(Arrays.asList(
                    new CreateNewPlayerInstance(this, this),
                    new SetAssetsDataSourceMessage(this, mAssetFileDescriptor, this),
                    new Prepare(this, this),
                    new Start(this, this)
            ));
            mPlayerHandler.resumeQueueProcessing(TAG);
        }
    }

    private void stopImpl() {
        mPlayerHandler.pauseQueueProcessing(TAG);
        mPlayerHandler.clearAllPendingMessages(TAG);
        switch (mCurrentPlayerState) {
            case SETTING_NEW_PLAYER:
            case IDLE:

            case CREATING_PLAYER_INSTANCE:
            case PLAYER_INSTANCE_CREATED:

            case CLEARING_PLAYER_INSTANCE:
            case PLAYER_INSTANCE_CLEARED:
                break;
            case INITIALIZED:
            case PREPARING:
            case PREPARED:
            case STARTING:
            case STARTED:
            case PAUSING:
            case PAUSED:
                mPlayerHandler.addMessage(new Stop(this, this));
                //FALL-THROUGH

            case SETTING_DATA_SOURCE:
            case DATA_SOURCE_SET:
                /** if we don't reset player in this state, will will get 0;0 from {@link android.media.MediaPlayer.OnVideoSizeChangedListener}.
                 *  And this TextureView will never recover */
            case STOPPING:
            case STOPPED:
            case ERROR: // reset if error
                mPlayerHandler.addMessage(new Reset(this, this));
                //FALL-THROUGH
            case RESETTING:
            case RESET:
                mPlayerHandler.addMessage(new Release(this, this));
                //FALL-THROUGH
            case RELEASING:
            case RELEASED:
                mPlayerHandler.addMessage(new ClearPlayerInstance(this, this));

                break;
            case END:
            case PLAYBACK_COMPLETED:
                throw new RuntimeException("unhandled " + mCurrentPlayerState);
        }
        mPlayerHandler.resumeQueueProcessing(TAG);
    }
}

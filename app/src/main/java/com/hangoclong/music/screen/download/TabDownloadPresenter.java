package com.hangoclong.music.screen.download;

import android.content.Context;
import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.data.repository.TrackRepository;
import com.hangoclong.music.data.source.RequestDataCallback;

/**
 * Created by Admin on 3/21/2018.
 */

public class TabDownloadPresenter implements TabDownloadContract.Presenter{

    private TabDownloadContract.View mView;
    private TrackRepository mTrackRepository;
     TabDownloadPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(TabDownloadContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
        //No-op
    }

    @Override
    public void onStop() {
        //No-op
    }

    @Override
    public void getTracksFromLocal(Context context) {
        mTrackRepository.getAllTracksFromLocal(context, new RequestDataCallback<Collection>() {
            @Override
            public void onSuccess(Collection data) {
                mView.showAllTracksFromLocal(data);
            }

            @Override
            public void onFail(Exception e) {
                mView.showNoTracks();
            }
        });
    }
}

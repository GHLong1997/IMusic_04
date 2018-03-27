package com.hangoclong.music.screen.search;

import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.data.repository.TrackRepository;
import com.hangoclong.music.data.source.RequestDataCallback;

/**
 * Created by Admin on 3/16/2018.
 */

public class TabSearchPresenter implements TabSearchContract.Presenter {

    private TabSearchContract.View mView;
    private TrackRepository mTrackRepository;

    TabSearchPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(TabSearchContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
        //TODO
    }

    @Override
    public void onStop() {
        //TODO
    }

    @Override
    public void searchTracks(String href) {
        mTrackRepository.searchTracks(href, new RequestDataCallback<Collection>() {
            @Override
            public void onSuccess(Collection data) {
                if (data.getTrackList() != null) {
                    mView.showTracks(data);
                } else {
                    mView.showNoTrack();
                }
            }

            @Override
            public void onFail(Exception e) {
                mView.onError(e);
            }
        });
    }

    @Override
    public void loadMoreDataTrackList(String nextHref) {
        mTrackRepository.loadMoreDataTrackList(nextHref, new RequestDataCallback<Collection>() {
            @Override
            public void onSuccess(Collection data) {
                mView.updateTrackList(data);
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }
}

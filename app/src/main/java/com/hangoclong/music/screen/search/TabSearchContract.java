package com.hangoclong.music.screen.search;

import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.screen.BasePresenter;

/**
 * Created by Admin on 3/16/2018.
 */

public interface TabSearchContract {
    interface View {
        void showNoTrack();

        void showTracks(Collection collection);

        void updateTrackList(Collection collection);

        void onError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {
        void searchTracks(String href);

        void loadMoreDataTrackList(String nextHref);
    }
}

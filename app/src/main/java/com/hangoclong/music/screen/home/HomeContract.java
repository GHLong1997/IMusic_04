package com.hangoclong.music.screen.home;

import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.data.model.Track;
import com.hangoclong.music.screen.BasePresenter;
import com.hangoclong.music.utils.Constant;
import java.util.List;

/**
 * Created by Admin on 3/20/2018.
 */

public interface HomeContract {

    interface View {

        void showSlider(List<Track> trackList);

        void showAlternativeTrackList(Collection collection);

        void showAmbientTrackList(Collection collection);

        void showClassicalTrackList(Collection collection);

        void showCountryTrackList(Collection collection);

        void updateTrackList(Collection collection, @Constant.Genres String genre);

        void onError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {

        void getTrendingTrackList();

        void getTrackListByGenre(@Constant.Genres String genre);

        void loadMoreDataTrackList(String nextHref, @Constant.Genres String genre);
    }
}

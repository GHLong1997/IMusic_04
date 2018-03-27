package com.hangoclong.music.screen.download;

import android.content.Context;
import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.screen.BasePresenter;

/**
 * Created by Admin on 3/21/2018.
 */

public interface TabDownloadContract {

    interface View {
        void showAllTracksFromLocal(Collection collection);
        void showNoTracks();
    }

    interface Presenter extends BasePresenter<View> {
        void getTracksFromLocal(Context context);
    }
}

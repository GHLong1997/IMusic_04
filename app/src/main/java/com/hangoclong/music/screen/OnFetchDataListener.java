package com.hangoclong.music.screen;

/**
 * Created by Admin on 3/13/2018.
 */

public interface OnFetchDataListener {
    void onFetchDataSuccess(String data);

    void onFail(Exception e);
}

package com.hangoclong.music.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hangoclong.music.screen.download.TabDownloadFragment;
import com.hangoclong.music.screen.home.HomeFragment;
import com.hangoclong.music.screen.search.TabSearchFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/8/2018.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

     MainAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new TabSearchFragment());
        mFragmentList.add(new TabDownloadFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }
}

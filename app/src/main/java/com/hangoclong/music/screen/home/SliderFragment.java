package com.hangoclong.music.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hangoclong.music.R;
import com.hangoclong.music.data.model.Track;
import com.hangoclong.music.screen.BaseFragment;

/**
 * Created by Admin on 3/9/2018.
 */

public class SliderFragment extends BaseFragment {

    private static final String ARGUMENT_ARTWORK = "BUNDLE_ARTWORK_URL";
    private ImageView mImageViewSlider;

    public static SliderFragment newInstance(Track track) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_ARTWORK, track.getArtworkUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        initViews(view);
        Bundle args = getArguments();
        if (args != null) {
            Glide.with(view.getContext())
                    .load(args.getString(ARGUMENT_ARTWORK))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_logo))
                    .into(mImageViewSlider);
        }
        return view;
    }

    private void initViews(View view) {
        mImageViewSlider = view.findViewById(R.id.image_slider);
    }
}

package com.hangoclong.music.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hangoclong.music.R;
import com.hangoclong.music.data.model.Track;
import com.hangoclong.music.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/10/2018.
 */

public class HomeAdapter extends BaseRecyclerViewAdapter<HomeAdapter.ViewHolder> {

    private static final int BEGIN_INDEX = 0;
    private static final int END_INDEX = 11;
    private List<Track> mTrackList = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    HomeAdapter(Context context, ItemClickListener itemClickListener) {
        super(context);
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.setData(mTrackList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTrackList != null ? mTrackList.size() : 0;
    }

    public void addData(@NonNull List<Track> trackList) {
        mTrackList.addAll(trackList);
        notifyDataSetChanged();
    }

    List<Track> getData() {
        return mTrackList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewTrack;
        private TextView mTextViewTrackName;
        private Track mTrack;

        ViewHolder(View itemView, final ItemClickListener itemClickListener) {
            super(itemView);
            mImageViewTrack = itemView.findViewById(R.id.image_track);
            mTextViewTrackName = itemView.findViewById(R.id.text_track_name);
            itemView.setOnClickListener(
                    view -> itemClickListener.onItemClicked(mTrack, getAdapterPosition()));
        }

        void setData(Track track) {
            if (track == null) {
                return;
            }
            mTrack = track;
            Glide.with(itemView.getContext())
                    .load(track.getArtworkUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_logo))
                    .into(mImageViewTrack);
            mTextViewTrackName.setText(track.getTitle());

        }
    }

    public interface ItemClickListener {
        void onItemClicked(Track track, int position);
    }
}

package com.hangoclong.music.data.source.remote;

import android.content.Context;
import android.util.Log;
import com.hangoclong.music.data.model.Artist;
import com.hangoclong.music.data.model.Collection;
import com.hangoclong.music.data.model.Track;
import com.hangoclong.music.data.source.RequestDataCallback;
import com.hangoclong.music.data.source.TrackDataSource;
import com.hangoclong.music.data.source.remote.config.service.DownloadFileFromUrl;
import com.hangoclong.music.data.source.remote.config.service.FetchDataFromURL;
import com.hangoclong.music.screen.OnDownloadListener;
import com.hangoclong.music.screen.OnFetchDataListener;
import com.hangoclong.music.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 3/20/2018.
 */

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {

    private static TrackRemoteDataSource mTrackRemoteDataSource;
    private static final String TAG = "Error";

    public static synchronized TrackRemoteDataSource getInstance() {
        if (mTrackRemoteDataSource == null) {
            mTrackRemoteDataSource = new TrackRemoteDataSource();
        }
        return mTrackRemoteDataSource;
    }

    @Override
    public void getTrendingTrackList(RequestDataCallback<Collection> callback) {
        new FetchDataFromURL(new OnFetchDataListener() {
            @Override
            public void onFetchDataSuccess(String data) {
                Collection collection = parseJSON(data);
                callback.onSuccess(collection);
            }

            @Override
            public void onFail(Exception e) {
                callback.onFail(e);
            }
        }).execute(Constant.TRENDING_TRACK_URL);
    }

    @Override
    public void getTrackListByGenre(String genre, RequestDataCallback<Collection> callback) {
        new FetchDataFromURL(new OnFetchDataListener() {
            @Override
            public void onFetchDataSuccess(String data) {
                Collection collection = parseJSON(data);
                callback.onSuccess(collection);
            }

            @Override
            public void onFail(Exception e) {
                callback.onFail(e);
            }
        }).execute(Constant.TRACK_GENRES_URL + genre);
    }

    @Override
    public void loadMoreDataTrackList(String nextHref, RequestDataCallback<Collection> callback) {
        new FetchDataFromURL(new OnFetchDataListener() {
            @Override
            public void onFetchDataSuccess(String data) {
                Collection collection = parseJSON(data);
                callback.onSuccess(collection);
            }

            @Override
            public void onFail(Exception e) {
                callback.onFail(e);
            }
        }).execute(nextHref);
    }

    @Override
    public void downloadTrack(Context context, String url, String fileName,
            RequestDataCallback<String> callback) {
        new DownloadFileFromUrl(context, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String message) {
                callback.onSuccess(message);
            }

            @Override
            public void onDownloadFail() {
                callback.onFail(null);
            }
        }).startDownload(context, url, fileName);
    }

    @Override
    public void searchTracks(String href, RequestDataCallback<Collection> callback) {
        new FetchDataFromURL(new OnFetchDataListener() {
            @Override
            public void onFetchDataSuccess(String data) {
                Collection collection = parseJSON(data);
                callback.onSuccess(collection);
            }

            @Override
            public void onFail(Exception e) {
                callback.onFail(e);
            }
        }).execute(href);
    }

    private Collection parseJSON(String jsonString) {
        Collection collection = new Collection();
        List<Track> trackList = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject1.getJSONArray(Constant.COLLECTION);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Track track = new Track();
                track.setId(jsonObject.getInt(Constant.ID));
                track.setKind(jsonObject.getString(Constant.KIND));
                track.setUri(jsonObject.getString(Constant.URI));
                track.setUserId(jsonObject.getInt(Constant.USER_ID));
                track.setGenre(jsonObject.getString(Constant.GENRE));
                track.setTitle(jsonObject.getString(Constant.TITLE));
                track.setStreamUrl(jsonObject.getString(Constant.STREAM_URL));
                track.setArtworkUrl(jsonObject.getString(Constant.ARTWORK_URL));
                track.setDownloadable(jsonObject.getBoolean(Constant.DOWNLOADABLE));
                JSONObject userJsonObject = jsonObject.getJSONObject(Constant.USER);
                Artist artist = new Artist();
                artist.setAvatarUrl(userJsonObject.getString(Constant.AVATAR_URL));
                artist.setId(userJsonObject.getInt(Constant.ID));
                artist.setUsername(userJsonObject.getString(Constant.USER_NAME));
                track.setUser(artist);
                trackList.add(track);
            }
            String nextHref = jsonObject1.getString(Constant.NEXT_HREF);
            collection.setTrackList(trackList);
            collection.setNextHref(nextHref);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return collection;
    }
}

package com.terabyte.annaflix;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadsFragment extends Fragment {
    private Context mContext;
    private Fetch fetch;

    public DownloadsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_downloads, container, false);
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(mContext)
                .setDownloadConcurrentLimit(3)
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);

        String url = "https://storage.kanzaki.ru/ANIME___/Accel_World/%28Hi10%29_Accel_World_-_22_%28720p%29_%28UTW%29.mkv";
        String file = "/downloads/test1.mkv";

        final Request request = new Request(url, file);
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");

        fetch.enqueue(request, updatedRequest -> {
            //Request was successfully enqueued for download.
        }, error -> {
            //An error occurred enqueuing the request.
        });



       return view;
    }
}

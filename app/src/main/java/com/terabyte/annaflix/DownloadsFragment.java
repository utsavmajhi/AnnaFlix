package com.terabyte.annaflix;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.download.library.DownloadImpl;
import com.download.library.DownloadListener;
import com.download.library.DownloadingListener;
import com.download.library.Extra;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.huxq17.download.Pump;
import com.huxq17.download.core.DownloadInfo;


import java.io.File;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadsFragment extends Fragment implements DownloadListener {
    Button b1;
    public DownloadsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view=inflater.inflate(R.layout.fragment_downloads, container, false);
       b1=view.findViewById(R.id.l);
       PRDownloader.initialize(getContext());
       TextView t1=view.findViewById(R.id.d1txt);
       b1.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {

               /*
               //Pump Downloader (Issue:Live data refreshing not showing)
               Pump.newRequest("https://storage.kanzaki.ru/ANIME___/AKB0048/%28Hi10%29_AKB0048_-_17_%28720p%29_%28EveTaku%29.mkv")
                       //Set id,optionally
                       //Set DownloadTaskExecutor,optionally
                       //Set listener,optionally
                       .listener(new DownloadListener() {
                           @Override
                           public void onSuccess() {
                               Toast.makeText(getContext(), "Successfully completed", Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void onFailed() {
                               Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void onProgress(int progress) {
                               DownloadInfo downloadInfo = getDownloadInfo();
                               //Get download file
                               File downloadFile = new File(downloadInfo.getFilePath());
                               //Get download url
                               String url = downloadInfo.getUrl();
                               //Get download speed
                               String speed = downloadInfo.getSpeed();
                              // t1.setText(speed);
                           }
                       })
                       .submit();

               */
               /*
               String dirPath = getContext().getFilesDir().getAbsolutePath()+File.separator+"downloads";
               String url="https://storage.kanzaki.ru/ANIME___/AKB0048/%28Hi10%29_AKB0048_-_17_%28720p%29_%28EveTaku%29.mkv";
               int downloadId = PRDownloader.download(url,dirPath, "Test2.mkv")
                       .build()
                       .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                           @Override
                           public void onStartOrResume() {

                           }
                       })
                       .setOnPauseListener(new OnPauseListener() {
                           @Override
                           public void onPause() {

                           }
                       })
                       .setOnCancelListener(new OnCancelListener() {
                           @Override
                           public void onCancel() {

                           }
                       })
                       .setOnProgressListener(new OnProgressListener() {
                           @Override
                           public void onProgress(Progress progress) {
                               String b=progress.toString();
                               System.out.println(b);
                           }
                       })
                       .start(new OnDownloadListener() {
                           @Override
                           public void onDownloadComplete() {
                               System.out.println("Completed Successfully");
                               Toast.makeText(getContext(), "Successfully downloaded", Toast.LENGTH_LONG).show();
                           }

                           @Override
                           public void onError(Error error) {
                               System.out.println(error);

                           }
                       });
               */
               DownloadImpl.getInstance()
                       .with(getContext())
                       .url("https://storage.kanzaki.ru/ANIME___/AKB0048/%28Hi10%29_AKB0048_-_17_%28720p%29_%28EveTaku%29.mkv")
                       .setForceDownload(true)
                       .enqueue(new DownloadingListener() {
                           @Override
                           public void onProgress(String url, long downloaded, long length, long usedTime) {

                               System.out.println("Downloaded="+downloaded+"usedTime="+usedTime);

                           }
                       });


           }

       });

       return view;
    }


    private void Prdown() {

    }


    @Override
    public void onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, Extra extra) {

    }

    @Override
    public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
        return false;
    }
}

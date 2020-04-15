package com.terabyte.annaflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public class homepage extends AppCompatActivity implements FetchListener {
    BubbleNavigationLinearView bubbleNavigation;
    Fragment selectedFragment=null;
    private Fetch fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(3)
                .build();
        Fetch fetch = Fetch.Impl.getInstance(fetchConfiguration);






        bubbleNavigation=findViewById(R.id.bottom_navigation_view_linear);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position)
                {
                    case 0:
                        selectedFragment=new HomeFragment();
                        break;
                    case 1:
                        selectedFragment=new SearchFragment();
                        break;
                    case 2:
                        selectedFragment=new NotificationsFragment();
                        break;
                    case 3:
                        selectedFragment=new DownloadsFragment();
                        break;
                    case 4:
                        selectedFragment=new NotificationsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            }
        });
    }

    public void l1click(View view) {
        Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();

        String url = "https://storage.kanzaki.ru/ANIME___/Accel_World/%28Hi10%29_Accel_World_-_22_%28720p%29_%28UTW%29.mkv";

        File folder=new File(Environment.getExternalStorageDirectory()+"/ANIMES");
        if(!(folder.exists()))
        {
            folder.mkdir();
        }
        File folderanime=new File(folder,"My animes");
        File childFile =new File(folderanime,"anime1");




        final Request request = new Request(url, childFile.getPath()+".mkv");
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");


        fetch.enqueue(request, updatedRequest -> {
            Toast.makeText(this, "Call is made", Toast.LENGTH_SHORT).show();
            //Request was successfully enqueued for download.
        }, error -> {
            Toast.makeText(this, "Error:", Toast.LENGTH_SHORT).show();
            //An error occurred enqueuing the request.
        });
        fetch.addListener(this);
    }
    public void l2clicl(View view) {
    }

    @Override
    public void onAdded(@NotNull Download download) {
        showNotification(download.getId());

    }

    Notification.Builder builder;
    NotificationManagerCompat nmc;
    private void showNotification(int id) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("channelid1","001", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("This is Description");

            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);

            builder=new Notification.Builder(getApplicationContext(),"channelid");
            builder.setContentTitle("Downloading File");
            builder.setAutoCancel(false);
            builder.setProgress(100,0,false);
            builder.setWhen(System.currentTimeMillis());
            builder.setPriority(Notification.PRIORITY_DEFAULT);

            nmc=NotificationManagerCompat.from(getApplicationContext());
            nmc.notify(id,builder.build());

        }
        else
        {
            builder=new Notification.Builder(getApplicationContext());
            builder.setContentTitle("Downloading File");
            builder.setAutoCancel(false);
            builder.setProgress(100,0,false);
            builder.setWhen(System.currentTimeMillis());
            builder.setPriority(Notification.PRIORITY_DEFAULT);

            nmc=NotificationManagerCompat.from(getApplicationContext());
            nmc.notify(id,builder.build());

        }
    }

    @Override
    public void onCancelled(@NotNull Download download) {

    }

    @Override
    public void onCompleted(@NotNull Download download) {
        Toast.makeText(this, "Successfully Completed", Toast.LENGTH_SHORT).show();
        if(builder!=null)
        {
                builder.setContentText("Download Finished");
                builder.setProgress(0,0,false);
                nmc.notify(download.getId(),builder.build());
        }
    }

    @Override
    public void onDeleted(@NotNull Download download) {

    }

    @Override
    public void onDownloadBlockUpdated(@NotNull Download download, @NotNull DownloadBlock downloadBlock, int i) {

    }

    @Override
    public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {

    }

    @Override
    public void onPaused(@NotNull Download download) {

    }

    @Override
    public void onProgress(@NotNull Download download, long l, long l1) {

        int progress=download.getProgress();
            builder.setProgress(100,progress,false);
            nmc.notify(download.getId(),builder.build());
    }

    @Override
    public void onQueued(@NotNull Download download, boolean b) {

    }

    @Override
    public void onRemoved(@NotNull Download download) {

    }

    @Override
    public void onResumed(@NotNull Download download) {

    }

    @Override
    public void onStarted(@NotNull Download download, @NotNull List<? extends DownloadBlock> list, int i) {

    }

    @Override
    public void onWaitingNetwork(@NotNull Download download) {

    }
}

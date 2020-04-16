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

public class homepage extends AppCompatActivity  {
    BubbleNavigationLinearView bubbleNavigation;
    Fragment selectedFragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
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

}

package com.terabyte.annaflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.terabyte.annaflix.EpisodesModel.EpisodesAdapter;
import com.terabyte.annaflix.EpisodesModel.episodeitemmodel;

import java.util.ArrayList;
import java.util.List;

public class ClickedItemEnter extends AppCompatActivity {
    RecyclerView recyclerView;
    private EpisodesAdapter mallepisodeadpater;
    private ArrayList<episodeitemmodel> mallepisodelist;
    private FirebaseFirestore db;
    private TextView selectanimetitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_item_enter);

        Intent intent=getIntent();
        String animetitle=intent.getStringArrayExtra("ID_EXTRA")[0];
        String animedescription=intent.getStringArrayExtra("ID_EXTRA")[1];


        selectanimetitle=findViewById(R.id.itemanimetitle);

        selectanimetitle.setText(animetitle);


        db=FirebaseFirestore.getInstance();
        mallepisodelist=new ArrayList<>();
        recyclerView=findViewById(R.id.episodesrecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mallepisodeadpater);
        //call the episodes
        firebaseepisodescollector(animetitle);
    }

    //backend starts
    private void firebaseepisodescollector(String animetitleurl) {
            db.collection("EpisodesData").document(animetitleurl).collection("Episodes")
                .orderBy("EpisodeTitle").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if(!queryDocumentSnapshots.isEmpty())
                    {
                        String vlink;
                        String eptitle;
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        //started from 1 to ignore ../ files
                        for(int i=1;i<list.size();i++)
                        {
                            vlink="https://storage.kanzaki.ru/ANIME___/"+animetitleurl+"/"+list.get(i).get("VideoLink").toString();
                            eptitle="Episode "+i;
                            mallepisodelist.add(new episodeitemmodel(eptitle,vlink,animetitleurl," "," "));

                        }
                        mallepisodeadpater=new EpisodesAdapter(getBaseContext(),mallepisodelist);
                        recyclerView.setAdapter(mallepisodeadpater);
                    }
                }
            });
    }
}

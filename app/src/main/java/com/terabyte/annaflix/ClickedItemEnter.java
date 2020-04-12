package com.terabyte.annaflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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

        selectanimetitle=findViewById(R.id.itemanimetitle);
        db=FirebaseFirestore.getInstance();
        mallepisodelist=new ArrayList<>();
        recyclerView=findViewById(R.id.episodesrecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mallepisodeadpater);
        //call the episodes
        firebaseepisodescollector();
    }

    //backend starts
    private void firebaseepisodescollector() {
            db.collection("EpisodesData").document("Antique_Bakery").collection("Episodes")
                .orderBy("EpisodeTitle").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if(!queryDocumentSnapshots.isEmpty())
                    {
                        String vlink;
                        String animetitle="Antique Bakery";
                        selectanimetitle.setText(animetitle);
                        String eptitle;
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        //started from 1 to ignore ../ files
                        for(int i=1;i<list.size();i++)
                        {
                            vlink=list.get(i).get("VideoLink").toString();
                            eptitle="Episode "+i;
                            mallepisodelist.add(new episodeitemmodel(eptitle,vlink," "," "," "));

                        }
                        mallepisodeadpater=new EpisodesAdapter(getApplicationContext(),mallepisodelist);
                        recyclerView.setAdapter(mallepisodeadpater);
                    }
                }
            });
    }
}

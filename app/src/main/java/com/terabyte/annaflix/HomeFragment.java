package com.terabyte.annaflix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private HmRecrecylerAdapter mrecoanimeadpater;
    private ArrayList<recommanimitemModel> mallanimelist;
    RecyclerView hmRECOrecyclerView;
    private FirebaseFirestore db;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        mallanimelist=new ArrayList<>();
        hmRECOrecyclerView=view.findViewById(R.id.recommendationrecyler);
        db=FirebaseFirestore.getInstance();

        LinearLayoutManager horizontalLayoutManager  = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        hmRECOrecyclerView.setLayoutManager(horizontalLayoutManager);

        //set Recommended animes
        recommendedanimes();

        return view;
    }


    //backend starts from firebase
    private void recommendedanimes() {

        db.collection("Animedata").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> listofanimes=queryDocumentSnapshots.getDocuments();
                    for(int i=0;i<listofanimes.size();i++)
                    {
                        String animtitle=listofanimes.get(i).get("Title").toString();
                        String animimgurl=listofanimes.get(i).get("Title").toString();
                        String animdescrip=listofanimes.get(i).get("Title").toString();
                        String animyear=listofanimes.get(i).get("Title").toString();

                        mallanimelist.add(new recommanimitemModel(animtitle,animyear,"",animimgurl,"",animdescrip,""));

                    }
                    mrecoanimeadpater =new HmRecrecylerAdapter(getContext(),mallanimelist);
                    hmRECOrecyclerView.setAdapter(mrecoanimeadpater);
                }
            }
        });

    }
}

package com.terabyte.annaflix;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private HmRecrecylerAdapter mrecoanimeadpater;
    private ArrayList<recommanimitemModel> mallanimelist;
    RecyclerView hmRECOrecyclerView;
    SliderLayout sliderLayout;
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
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(3); //set scroll delay in seconds :

        setSliderViews();

        //set Recommended animes
        recommendedanimes();

        return view;
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:

                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/animedatabase-b85a7.appspot.com/o/fate.jpg?alt=media&token=f60756bb-8029-4766-bffa-10c465ccb1df");
                    break;

                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/animedatabase-b85a7.appspot.com/o/charlotte.jpg?alt=media&token=fa991701-c429-436a-9f21-7c269955d3c6");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/animedatabase-b85a7.appspot.com/o/onepunch.jpg?alt=media&token=633a4807-5dec-4849-8dfe-b3cd71cd8b2f");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/animedatabase-b85a7.appspot.com/o/kimi.jpg?alt=media&token=68a01453-4381-4975-9adf-71fbc70b6915");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            if(i==0)
            {
                sliderView.setDescription("Coming Soon");
                sliderView.setDescriptionTextSize(19);
            }

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    //Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
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

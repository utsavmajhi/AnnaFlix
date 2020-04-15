package com.terabyte.annaflix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.terabyte.annaflix.hmrecyclermodels2.Hmrecycler2Adapter;
import com.terabyte.annaflix.hmrecyclermodels2.hmrecycleranimeitemModel;
import com.terabyte.annaflix.hmrecyler1models.HmRecrecylerAdapter;
import com.terabyte.annaflix.hmrecyler1models.recommanimitemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private HmRecrecylerAdapter mrecoanimeadpater;
    private Hmrecycler2Adapter mrecy2animeadapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<recommanimitemModel> mallanimelist;
    private ArrayList<hmrecycleranimeitemModel> mallanimelist2;


    RecyclerView hmRECOrecyclerView;
    RecyclerView hmrecyler2;

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
        mallanimelist2=new ArrayList<>();
        swipeRefreshLayout=view.findViewById(R.id.homeswiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                recommendedanimes();
                animeslistload2();
            }
        });
        hmRECOrecyclerView=view.findViewById(R.id.recommendationrecyler);
        hmrecyler2=view.findViewById(R.id.r2);

        //firestore initialise
        db=FirebaseFirestore.getInstance();
        //firestore initialise complete

        //recyclers iniatiation
        LinearLayoutManager horizontalLayoutManager  = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        hmRECOrecyclerView.setLayoutManager(horizontalLayoutManager);
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        hmrecyler2.setLayoutManager(horizontalLayoutManager1);
        //recyclers iniation complete

        //imageslider
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(3); //set scroll delay in seconds :
        setSliderViews();
        //image slider
        //set Recommended animes
        recommendedanimes();
        animeslistload2();
        //views ended
        return view;
    }

    private void animeslistload2() {
        mallanimelist2.clear();
        db.collection("Animedata").orderBy("YearofRelease").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> listofanimes=queryDocumentSnapshots.getDocuments();
                    for(int i=listofanimes.size()-1;i>=0;i--)
                    {
                        String animtitle=listofanimes.get(i).get("Title").toString();
                        String animimgurl=listofanimes.get(i).get("Title").toString();
                        String animdescrip=listofanimes.get(i).get("Title").toString();
                        String animyear=listofanimes.get(i).get("Title").toString();


                        mallanimelist2.add(new hmrecycleranimeitemModel(animtitle,animyear,"",animimgurl,"",animdescrip,""));

                    }
                    mrecy2animeadapter= new Hmrecycler2Adapter(getContext(),mallanimelist2);

                    hmrecyler2.setAdapter(mrecy2animeadapter);
                    mrecy2animeadapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
       // swipeRefreshLayout.setRefreshing(false);
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
        mallanimelist.clear();
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
                    mrecoanimeadpater.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }


    @Override
    public void onRefresh() {
        recommendedanimes();
        animeslistload2();
    }
}

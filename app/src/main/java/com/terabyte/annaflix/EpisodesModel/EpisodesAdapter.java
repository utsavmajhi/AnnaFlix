package com.terabyte.annaflix.EpisodesModel;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.terabyte.annaflix.R;
import com.terabyte.annaflix.exoplayer;

import java.util.ArrayList;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {
    public EpisodesAdapter(Context mContext, ArrayList<episodeitemmodel> mallepisodeitemlist) {
        this.mContext = mContext;
        this.mallepisodeitemlist = mallepisodeitemlist;
    }

    Context mContext;
    private ArrayList<episodeitemmodel> mallepisodeitemlist;
    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.single_episode_item,parent,false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, final int position) {


        holder.episodename.setText(mallepisodeitemlist.get(position).title);
        //click item listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                episodeitemmodel m=mallepisodeitemlist.get(position);
                String sendvideourl=m.getVideolink();
                Intent i=new Intent(view.getContext(), exoplayer.class);
                i.putExtra("ID_EXTRA",new String[]{sendvideourl});
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        if(!(mallepisodeitemlist==null))
        {
            return mallepisodeitemlist.size();
        }
        else
        {
            return 0;
        }

    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        TextView episodename;


        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            episodename=itemView.findViewById(R.id.selectepisodename);


        }
    }

}

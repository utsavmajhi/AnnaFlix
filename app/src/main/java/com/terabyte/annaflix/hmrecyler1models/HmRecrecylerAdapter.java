package com.terabyte.annaflix.hmrecyler1models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.terabyte.annaflix.ClickedItemEnter;
import com.terabyte.annaflix.R;

import java.util.ArrayList;

public class HmRecrecylerAdapter extends RecyclerView.Adapter<HmRecrecylerAdapter.AnimeViewHolder> {
    public HmRecrecylerAdapter(Context mContext, ArrayList<recommanimitemModel> mallanimeitemlist) {
        this.mContext = mContext;
        this.mallanimeitemlist = mallanimeitemlist;
    }

    Context mContext;
    private ArrayList<recommanimitemModel> mallanimeitemlist;
    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.single_anime_title,parent,false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, final int position) {


        String dirtystr= mallanimeitemlist.get(position).getTitle();
        String cleanedstr = dirtystr.replaceAll("[^a-zA-Z0-9]", " ");
        holder.atitle.setText(cleanedstr);
        //click item listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommanimitemModel m=mallanimeitemlist.get(position);
                String sendtitle=m.getTitle();
                String senddescip=m.getDescrip();
                Intent i=new Intent(view.getContext(), ClickedItemEnter.class);
                i.putExtra("ID_EXTRA",new String[]{sendtitle,senddescip});
                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {

        if(!(mallanimeitemlist==null))
        {
            return mallanimeitemlist.size();
        }
        else
        {
            return 0;
        }

    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private TextView atitle;
        private TextView ayear;
        private ImageView aimage;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();

            atitle=itemView.findViewById(R.id.animetitle);


        }
    }

}

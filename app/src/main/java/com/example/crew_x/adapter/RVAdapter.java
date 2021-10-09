package com.example.crew_x.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crew_x.R;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    ArrayList<String> name;
    ArrayList<String> agency;
    ArrayList<String> status;
    ArrayList<String> imgLink;
    ArrayList<String> link;

    public RVAdapter(ArrayList<String> name,ArrayList<String> agency,ArrayList<String> status,ArrayList<String> imgLink,ArrayList<String> link){
        this.name=name;
        this.agency=agency;
        this.status=status;
        this.imgLink=imgLink;
        this.link=link;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardview;
        public ViewHolder(CardView v){
            super(v);
            cardview=v;
        }
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        CardView cv=holder.cardview;

        TextView nameview=cv.findViewById(R.id.name);
        TextView agencyview=cv.findViewById(R.id.agency);
        TextView statusview=cv.findViewById(R.id.status);
        ImageView imgview=cv.findViewById(R.id.img);

        nameview.setText(  "Name   : "+name.get(position));
        agencyview.setText("Agency : "+agency.get(position));
        statusview.setText("Status : "+status.get(position));

        if(imgLink.get(position)!=null){
            Glide.with(cv)
                    .load(imgLink.get(position))
                    //.circleCrop()
                    .into(imgview);
        }

    }

    @Override
    public int getItemCount() {
        return name.size();
    }
}

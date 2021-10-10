package com.example.crew_x.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crew_x.R;
import com.example.crew_x.WebActivity;
import com.example.crew_x.data.AppDatabase;
import com.example.crew_x.data.CrewMembers;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    ArrayList<String> name;
    ArrayList<String> agency;
    ArrayList<String> status;
    ArrayList<String> imgLink;
    ArrayList<String> link;

    Context context;

    public RVAdapter(Context c){

        AppDatabase db=AppDatabase.getDbInsatance(c.getApplicationContext());

        List<CrewMembers> l= db.appDao().getAllMembers();

        ArrayList<String> n=new ArrayList<>();
        ArrayList<String> a=new ArrayList<>();
        ArrayList<String> s=new ArrayList<>();
        ArrayList<String> iL=new ArrayList<>();
        ArrayList<String> link=new ArrayList<>();

        for(CrewMembers cm:l){
            n.add(cm.name);
            a.add(cm.agency);
            s.add(cm.status);
            iL.add(cm.imgurl);
            link.add(cm.wikiurl);
        }

        this.name=n;
        this.agency=a;
        this.status=s;
        this.imgLink=iL;
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
        context=parent.getContext();
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
                    .into(imgview);
        }

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WebActivity.class);
                intent.putExtra(WebActivity.URL,link.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }
}

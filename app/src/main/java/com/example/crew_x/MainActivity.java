package com.example.crew_x;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crew_x.adapter.RVAdapter;
import com.example.crew_x.api.APIClient;
import com.example.crew_x.api.CREWAPI;
import com.example.crew_x.model.Crew;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv);


        Call<List<Crew>> call= APIClient.getClient().getCrewList();
        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                if(!response.isSuccessful()){
                    Log.e("RESPNOSE NOT SUCCESSFUL",""+response.code());
                    return;
                }
                List<Crew> list=response.body();
                ArrayList<String> name=new ArrayList<>();
                ArrayList<String> agency=new ArrayList<>();
                ArrayList<String> status=new ArrayList<>();
                ArrayList<String> imgLink=new ArrayList<>();
                ArrayList<String> link=new ArrayList<>();
                for(Crew l:list){
//                    String s=""+l.getName()+l.getAgency()+l.getStatus()+l.getImageUrl()+l.getWikiLink();
//                    Log.e("CREW",s);
                    name.add(l.getName());
                    agency.add(l.getAgency());
                    status.add(l.getStatus());
                    imgLink.add(l.getImageUrl());
                    link.add(l.getWikiLink());
                }
                LinearLayoutManager lm= new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(lm);
                recyclerView.setAdapter(new RVAdapter(name,agency,status,imgLink,link));
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {
                Log.e("RESPNOSE FAILURE",t.getMessage());
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

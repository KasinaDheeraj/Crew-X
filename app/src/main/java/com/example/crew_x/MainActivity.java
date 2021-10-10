package com.example.crew_x;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.crew_x.adapter.RVAdapter;
import com.example.crew_x.api.APIClient;
import com.example.crew_x.data.AppDatabase;
import com.example.crew_x.data.CrewMembers;
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

        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
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
                AppDatabase db=AppDatabase.getDbInsatance(MainActivity.this.getApplicationContext());

                for(Crew l:list){
                    CrewMembers cm=new CrewMembers();
                    cm.name=l.getName();
                    cm.agency=l.getAgency();
                    cm.status=l.getStatus();
                    cm.imgurl=l.getImageUrl();
                    cm.wikiurl=l.getWikiLink();
                    cm.memid=l.getId();
                    db.appDao().insertMember(cm);
                }
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {
                Log.e("RESPNOSE FAILURE",t.getMessage());
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager lm= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new RVAdapter(MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.refreshOption){
            setUpRecyclerView();
        }else if(item.getItemId()==R.id.delete){
            AppDatabase db=AppDatabase.getDbInsatance(MainActivity.this.getApplicationContext());
            db.appDao().deleteAll();
        }
        return true;
    }
}

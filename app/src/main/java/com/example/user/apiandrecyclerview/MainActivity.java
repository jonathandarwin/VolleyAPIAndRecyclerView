package com.example.user.apiandrecyclerview;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ScheduleModel> listSchedule;
    RecyclerView rvSchedule;
    FloatingActionButton fabAdd;
    String URL = "https://itandroidbootcamp-api.herokuapp.com/api/schedule";

    ScheduleCustomAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSchedule = (RecyclerView) findViewById(R.id.rvSchedule);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);

        listSchedule = new ArrayList<>();

        rvSchedule.setHasFixedSize(true);
        rvSchedule.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ScheduleCustomAdapter(this, listSchedule);
        rvSchedule.setAdapter(adapter);

        LoadData();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertScheduleActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addData(){
        for(int i=0; i<4; i++){
            ScheduleModel schedule = new ScheduleModel("Title " + i, "Ini Description " + i, "Author " + i);
            listSchedule.add(schedule);
        }
    }


    public void LoadData()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject scheduleObject = response.getJSONObject(i);
                                ScheduleModel schedule = new ScheduleModel(
                                        scheduleObject.getString("title"),
                                        scheduleObject.getString("description"),
                                        scheduleObject.getString("author")
                                );
                                listSchedule.add(schedule);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                },
                null
        );


        requestQueue.add(jsonArrayRequest);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listSchedule.clear();
        LoadData();
        adapter.notifyDataSetChanged();
    }
}

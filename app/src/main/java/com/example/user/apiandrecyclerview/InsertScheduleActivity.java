package com.example.user.apiandrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class InsertScheduleActivity extends AppCompatActivity {

    EditText txtTitle, txtDescription, txtAuthor;
    Button btnSave;
    String URL = "https://itandroidbootcamp-api.herokuapp.com/api/schedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);

        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtAuthor = (EditText) findViewById(R.id.txtAuthor);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = txtTitle.getText().toString();
                String Description = txtDescription.getText().toString();
                String Author = txtAuthor.getText().toString();

                if(Title.equals("") || Description.equals("") || Author.equals("")){
                    Toast.makeText(InsertScheduleActivity.this, "Please input all the field", Toast.LENGTH_SHORT).show();
                }
                else{
                    // INSERT DATA TO WEB API
                    try{
                        JSONObject data = new JSONObject();

                        data.put("title", Title);
                        data.put("description", Description);
                        data.put("author", Author);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.POST,
                                URL,
                                data,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        finish();
                                    }
                                }
                                ,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(InsertScheduleActivity.this, "Error occur... Please try again", Toast.LENGTH_SHORT).show();
                                        Log.d("error", error.toString());
                                    }
                                }
                        );

                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(jsonObjectRequest);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }// END OF ELSE NO DATA
            }
        });
    }
}

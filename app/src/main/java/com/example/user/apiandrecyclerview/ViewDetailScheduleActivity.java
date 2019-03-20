package com.example.user.apiandrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDetailScheduleActivity extends AppCompatActivity {

    TextView tvDetailTitle, tvDetailDescription, tvDetailAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_schedule);

        Intent intent = getIntent();

        ScheduleModel schedule = new ScheduleModel(
                intent.getSerializableExtra("title").toString(),
                intent.getSerializableExtra("description").toString(),
                intent.getSerializableExtra("author").toString());

        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvDetailDescription = (TextView) findViewById(R.id.tvDetailDescription);
        tvDetailAuthor = (TextView) findViewById(R.id.tvDetailAuthor);


        tvDetailTitle.setText(schedule.getTitle());
        tvDetailDescription.setText(schedule.getDescription());
        tvDetailAuthor.setText(schedule.getAuthor());
    }
}

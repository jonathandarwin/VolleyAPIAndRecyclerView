package com.example.user.apiandrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by User on 12/4/2018.
 */

public class ScheduleCustomAdapter extends RecyclerView.Adapter<ScheduleCustomAdapter.ScheduleViewHolder> {

    Context mContext;
    ArrayList<ScheduleModel> listSchedule;

    public ScheduleCustomAdapter(Context mContext, ArrayList<ScheduleModel> listSchedule){
        this.mContext = mContext;
        this.listSchedule = listSchedule;
    }

    protected class ScheduleViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvAuthor;
        CardView cvSchedule;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            cvSchedule = (CardView) itemView.findViewById(R.id.cvSchedule);
        }
    }

    @NonNull
    @Override
    public ScheduleCustomAdapter.ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View customView = inflater.inflate(R.layout.cv_schedule, null);
        ScheduleViewHolder holder = new ScheduleViewHolder(customView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleCustomAdapter.ScheduleViewHolder holder, int position) {
        final ScheduleModel schedule = listSchedule.get(position);
        holder.tvTitle.setText(schedule.getTitle().toString());
        holder.tvAuthor.setText(schedule.getAuthor().toString());

        holder.cvSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewDetailScheduleActivity.class);
                intent.putExtra("title", schedule.getTitle().toString());
                intent.putExtra("description", schedule.getDescription().toString());
                intent.putExtra("author", schedule.getAuthor().toString());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSchedule.size();
    }
}

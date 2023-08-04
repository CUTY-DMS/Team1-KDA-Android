package com.example.cmd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.ScheduleItemResponse;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.ViewHolder> {

    private List<ScheduleItemResponse> items;

    public MonAdapter(List<ScheduleItemResponse> items) {
        this.items = items;
        Log.d("TEST","아이/"+items);
    }


    @NonNull
    @Override
    public MonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAdapter.ViewHolder holder, int position) {
        ScheduleItemResponse item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        Log.d("TEST","크기"+items.size());
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView schedule;
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schedule = itemView.findViewById(R.id.textView_schedule);
            time = itemView.findViewById(R.id.textView_schedule_time);
        }

        public void setItem(ScheduleItemResponse item) {
            Log.d("TEST","시간표 아이템/" + item);
            schedule.setText(item.getItrtCntnt());
            time.setText(item.getPerio());
        }
    }
}

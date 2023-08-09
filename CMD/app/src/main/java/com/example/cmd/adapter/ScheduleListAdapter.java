package com.example.cmd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.ScheduleItemResponse;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    private List<ScheduleItemResponse> items;

    public ScheduleListAdapter(List<ScheduleItemResponse> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ScheduleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleListAdapter.ViewHolder holder, int position) {
        ScheduleItemResponse item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView schedule;
        private final TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schedule = itemView.findViewById(R.id.textView_schedule);
            time = itemView.findViewById(R.id.textView_schedule_time);
        }

        public void setItem(ScheduleItemResponse item) {
            schedule.setText(item.getItrtCntnt());
            time.setText(item.getPerio());
        }
    }
}

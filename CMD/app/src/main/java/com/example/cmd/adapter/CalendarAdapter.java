package com.example.cmd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.CalendarResponse;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ItemViewHolder> {


    public List<CalendarResponse> list;

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;

        public LinearLayout linearLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.textView_calendar_date);
            title = itemView.findViewById(R.id.textview_calendar_title);

            linearLayout = itemView.findViewById(R.id.linear_calendar);
        }
    }

    public CalendarAdapter(List<CalendarResponse> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ItemViewHolder holder, int position) {

        holder.date.setText(list.get(position).getDate().toString());
        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

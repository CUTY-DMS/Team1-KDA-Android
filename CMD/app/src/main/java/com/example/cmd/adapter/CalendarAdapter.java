package com.example.cmd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.CalendarResponse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ItemViewHolder> {


    public List<CalendarResponse> list;

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;

        public TextView dateText;

        public LinearLayout linearLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.textView_calendar_date);
            title = itemView.findViewById(R.id.textview_calendar_title);
            dateText = itemView.findViewById(R.id.textView_calendar_dateText);

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

        Long year = list.get(position).getYear();
        Long month = list.get(position).getMonth();
        Long day = list.get(position).getDay();

        LocalDate date = LocalDate.of(Math.toIntExact(year), Math.toIntExact(month), Math.toIntExact(day));

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        holder.date.setText(day.toString());
        holder.title.setText(list.get(position).getTitle());
        holder.dateText.setText(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

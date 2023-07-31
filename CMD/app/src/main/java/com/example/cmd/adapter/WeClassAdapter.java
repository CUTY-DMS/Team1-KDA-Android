package com.example.cmd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.WeClassResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeClassAdapter extends RecyclerView.Adapter<WeClassAdapter.ItemViewHolder> {

    public List<WeClassResponse> list;


    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_weClass_user);
            date = itemView.findViewById(R.id.textview_weClass_date);
            title = itemView.findViewById(R.id.textview_weClass_alarm);
        }
    }

    public WeClassAdapter(List<WeClassResponse> list) {
        this.list =list;
        Log.d("TEST","list/" +list);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_class_item, parent, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("TEST","우리 반 알림/ name "+holder.name+"/date "+holder.date);
        Log.d("TEST","c/"+list.get(position).getTitle());



        String inputDateString = list.get(position).getDateTime();


        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yy.MM.dd");

        LocalDateTime dateTime = LocalDateTime.parse(inputDateString,input);
        String outDate = dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));

        holder.name.setText(list.get(position).getTitle());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(outDate);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

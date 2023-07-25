package com.example.cmd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.WeClassResponse;

import java.util.List;

public class WeClassAdapter extends RecyclerView.Adapter<WeClassAdapter.ItemViewHolder> {

    public List<WeClassResponse> list;


    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_allNotice_user);
            date = itemView.findViewById(R.id.textview_allNotice_date);
        }
    }

    public WeClassAdapter(List<WeClassResponse> list) {
        this.list =list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_class_item, parent, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if(holder.name != null || holder.date != null){
            holder.name.setText(list.get(position).getTitle());
            holder.date.setText(list.get(position).getDateTime());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

package com.example.cmd.adapter;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.response.AllNoticeResponse;

import java.util.ArrayList;
import java.util.List;

public class AllNoticeAdapter extends RecyclerView.Adapter<AllNoticeAdapter.ItemViewHolder> {

    public List<AllNoticeResponse> list;


    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_allNotice_user);
            date = itemView.findViewById(R.id.textview_allNotice_date);
        }
    }

    public AllNoticeAdapter(List<AllNoticeResponse> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_notice_item, parent, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDateTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

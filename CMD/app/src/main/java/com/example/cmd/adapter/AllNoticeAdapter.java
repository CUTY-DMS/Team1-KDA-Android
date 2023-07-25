package com.example.cmd.adapter;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.etc.AllNoticeDialog;
import com.example.cmd.response.AllNoticeResponse;

import java.util.ArrayList;
import java.util.List;

public class AllNoticeAdapter extends RecyclerView.Adapter<AllNoticeAdapter.ItemViewHolder> {

    public List<AllNoticeResponse> list;
    private AllNoticeDialog allNoticeDialog;


    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_allNotice_user);
            date = itemView.findViewById(R.id.textview_allNotice_date);


            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            layoutParams.dimAmount = 0.8f;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allNoticeDialog = new AllNoticeDialog(allNoticeDialog.getContext(), "다이얼로그 내용");
                    allNoticeDialog.show();
                }
            });
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

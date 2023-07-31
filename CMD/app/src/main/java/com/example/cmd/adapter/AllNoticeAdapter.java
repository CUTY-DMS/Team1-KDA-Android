package com.example.cmd.adapter;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.etc.AllNoticeDialog;
import com.example.cmd.fragment.AllNoticeFragment;
import com.example.cmd.response.AllNoticeResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AllNoticeAdapter extends RecyclerView.Adapter<AllNoticeAdapter.ItemViewHolder> {

    public List<AllNoticeResponse> list;
    private AllNoticeDialog allNoticeDialog;


    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_allNotice_user);
            date = itemView.findViewById(R.id.textview_allNotice_date);
            title = itemView.findViewById(R.id.textview_allNotice_alarm);


            //WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            //layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            //layoutParams.dimAmount = 0.8f;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allNoticeDialog = new AllNoticeDialog(v.getContext(), "다이얼로그 내용");
                    allNoticeDialog.show();

                }
            });
        }
    }

    public AllNoticeAdapter(List<AllNoticeResponse> list) {
        this.list = list;
        Log.d("TEST","allList"+list);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_notice_item, parent, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {


        String inputDateString = list.get(position).getDateTime();


        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yy.MM.dd");

        LocalDateTime dateTime = LocalDateTime.parse(inputDateString,input);
        String outDate = dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));

        Log.d("TEST","time"+outDate);

        Log.d("TEST","전체 알림/ name "+holder.name+"/date "+holder.date);

        holder.name.setText(list.get(position).getTitle());
        holder.date.setText(outDate);
        holder.title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

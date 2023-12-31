package com.example.cmd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.etc.NoticeDialog;
import com.example.cmd.response.WeClassResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeClassAdapter extends RecyclerView.Adapter<WeClassAdapter.ItemViewHolder> {

    public List<WeClassResponse> list;

    public WeClassAdapter(List<WeClassResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.we_class_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String inputDateString = list.get(position).getDateTime();

        DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(inputDateString, input);
        String outDate = dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));

        holder.name.setText(list.get(position).getName());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(outDate);

        holder.itemView.setOnClickListener(v -> {
            int clickPosition = holder.getAdapterPosition();

            NoticeDialog noticeDialog = new NoticeDialog(v.getContext(), list.get(clickPosition).getId());
            noticeDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_radious);
            noticeDialog.show();

            holder.linearLayout.setBackgroundResource(R.drawable.item_radious_click);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView title;
        public LinearLayout linearLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textview_weClass_user);
            date = itemView.findViewById(R.id.textview_weClass_date);
            title = itemView.findViewById(R.id.textview_weClass_alarm);

            linearLayout = itemView.findViewById(R.id.linear_weClass);
        }
    }
}

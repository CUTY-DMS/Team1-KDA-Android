package com.example.cmd.etc;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityAllNoticeDialogBinding;

public class AllNoticeDialog extends Dialog {

    private TextView title;
    private TextView date;
    private TextView detail;

    ActivityAllNoticeDialogBinding binding;


    public AllNoticeDialog(@NonNull Context context, String themeResId) {
        super(context);

        binding = ActivityAllNoticeDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = binding.textviewAllDialogUserTitle;
        date = binding.textviewAllDialogUserDate;
        detail = binding.textviewAllDialogUserDetail;

        detail.setText(themeResId);

        binding.buttonAllDialogShut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}

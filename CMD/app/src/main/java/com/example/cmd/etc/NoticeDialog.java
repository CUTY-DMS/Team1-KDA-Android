package com.example.cmd.etc;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cmd.activity.LoginActivity;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.ActivityAllNoticeDialogBinding;
import com.example.cmd.response.NoticeCheckResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDialog extends Dialog {

    ActivityAllNoticeDialogBinding binding;

    public NoticeDialog(@NonNull Context context, Long id) {
        super(context);

        binding = ActivityAllNoticeDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sever(id);

        binding.buttonAllDialogShut.setOnClickListener(v -> dismiss());
    }

    private void sever(Long id) {
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.check(LoginActivity.accessToken, id).enqueue(new Callback<NoticeCheckResponse>() {
            @Override
            public void onResponse(Call<NoticeCheckResponse> call, Response<NoticeCheckResponse> response) {
                if (response.isSuccessful()) {
                    String inputDateString = response.body().getDateTime();
                    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");

                    LocalDateTime dateTime = LocalDateTime.parse(inputDateString, input);
                    String outDate = dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));

                    binding.textviewAllDialogUserTitle.setText(response.body().getTitle());
                    binding.textviewAllDialogUserDate.setText(outDate);
                    binding.textviewAllDialogUserDetail.setText(response.body().getContents());
                    binding.textviewAllDialogUser.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<NoticeCheckResponse> call, Throwable t) {

            }
        });
    }
}

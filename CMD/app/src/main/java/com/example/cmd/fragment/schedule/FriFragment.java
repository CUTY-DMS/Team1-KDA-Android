package com.example.cmd.fragment.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.adapter.ScheduleListAdapter;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentFriBinding;
import com.example.cmd.response.ScheduleHisTimetable;
import com.example.cmd.response.ScheduleItemResponse;
import com.example.cmd.response.ScheduleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriFragment extends Fragment {

    private static final String BASE_URL = "https://open.neis.go.kr/hub/";
    private static final String KEY = "&KEY=513aa74951a64b0793c9a0519e3e4bde";
    private static String date;
    FragmentFriBinding binding;
    List<ScheduleItemResponse> scheduleItemResponseList;
    ScheduleListAdapter scheduleListAdapter;
    RecyclerView recyclerView;
    private String grade;
    private String classNm;

    public FriFragment(String grade, String classNm) {
        this.grade = grade;
        this.classNm = classNm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFriBinding.inflate(inflater);

        date = "20230707";

        recyclerView = binding.recyclerViewFri;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        scheduleItemResponseList = new ArrayList<>();
        scheduleListAdapter = new ScheduleListAdapter(scheduleItemResponseList);
        recyclerView.setAdapter(scheduleListAdapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SeverApi severApi = retrofit.create(SeverApi.class);

        Call<ScheduleResponse> call = severApi.scheduleList(grade, classNm, date, KEY);

        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()) {

                    ScheduleResponse scheduleResponse = response.body();

                    if (scheduleResponse != null) {
                        List<ScheduleHisTimetable> scheduleItems = scheduleResponse.getHisTimetable();

                        for (ScheduleHisTimetable item : scheduleItems) {


                            if (item.getScheduleItems() != null) {
                                scheduleItemResponseList.addAll(item.getScheduleItems());
                            }

                        }
                        scheduleListAdapter.notifyDataSetChanged();
                    }

                } else {
                    // API 호출 실패 처리
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }
}
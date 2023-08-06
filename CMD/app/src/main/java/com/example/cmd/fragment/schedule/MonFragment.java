package com.example.cmd.fragment.schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.ScheduleListAdapter;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentMonBinding;
import com.example.cmd.response.GradeClassResponse;
import com.example.cmd.response.ScheduleHisTimetable;
import com.example.cmd.response.ScheduleItemResponse;
import com.example.cmd.response.ScheduleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MonFragment extends Fragment {

    FragmentMonBinding binding;
    private static final String BASE_URL = "https://open.neis.go.kr/hub/";
    private static final String KEY = "&KEY=513aa74951a64b0793c9a0519e3e4bde";

    private String grade = "0";
    private String classNm = "0";
    private static String date;

    List<ScheduleItemResponse> scheduleItemResponseList;
    ScheduleListAdapter scheduleListAdapter;
    RecyclerView recyclerView;

    public MonFragment(String grade, String classNm) {
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
        binding = FragmentMonBinding.inflate(inflater);

        //grade = "1";
        //classNm = "3";
        date = "20230703";

        recyclerView = binding.recyclerViewMon;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        scheduleItemResponseList = new ArrayList<>();
        scheduleListAdapter = new ScheduleListAdapter(scheduleItemResponseList);
        Log.d("TEST","시간표 리스트/" + scheduleItemResponseList);
        recyclerView.setAdapter(scheduleListAdapter);


        gradeSever();
        scheduleSever();


        return binding.getRoot();
    }

    private void gradeSever() {

    }


    private void scheduleSever() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SeverApi severApi = retrofit.create(SeverApi.class);

        Call<ScheduleResponse> call = severApi.scheduleList(grade,classNm,date,KEY);

        call.enqueue(new Callback <ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()) {
                    //List<ScheduleResponse> scheduleList = response.body();

                    // JSON 배열을 JSON 객체로 래핑
                    JsonObject jsonObject = new JsonObject();
                    JsonArray jsonArray = new JsonArray();

                    Log.d("TEST","응답/"+response.body());
                    ScheduleResponse scheduleResponse = response.body();

                    if (scheduleResponse != null) {
                        List<ScheduleHisTimetable> scheduleItems = scheduleResponse.getHisTimetable();
                        //List<ScheduleItemResponse> itemResponseList = response.body();
                        //List<ScheduleResponse> responsesBody = response.body();
                        for (ScheduleHisTimetable item : scheduleItems) {
                            JsonObject scheduleObject = new JsonObject();
                            //scheduleObject.addProperty("ITRT_CNTNT", item.getScheduleItems().toString());
                            Log.d("TEST","d/"+response.body().getHisTimetable());
                            Log.d("TEST","아이템/"+item.getScheduleItems());
                            //String itrtCntnt = item.getItrtCntnt();
                            Log.d("TEST","f/"+scheduleItems);
                            //Log.d("TEST", "ITRT_CNTNT: " + itrtCntnt);
                            jsonArray.add(scheduleObject);

                            if(item.getScheduleItems() != null){
                                scheduleItemResponseList.addAll(item.getScheduleItems());
                            }


                        }

                        scheduleListAdapter.notifyDataSetChanged();
                    }
                    jsonObject.add("items", jsonArray);

                    // 결과 출력
                    Gson gson = new Gson();
                    String jsonObjectString = gson.toJson(jsonObject);
                    //Log.d("TEST","시단표/"+response.body().getPerio());
                    Log.d("TEST","시간표 url/ "+ response.raw().request().url().url());
                    Log.d("TEST", "시간표 JSON: " + jsonObjectString);
                } else {
                    // API 호출 실패 처리
                    Log.d("TEST", "API 호출 실패 코드: " + response.code());
                    Log.d("TEST", "연결 주소 확인: " + response.raw().request().url().url());
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                Log.d("TEST", "통신 실패: " + t.getMessage());
            }
        });
    }
}
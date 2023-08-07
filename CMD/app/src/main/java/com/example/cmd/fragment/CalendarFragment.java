package com.example.cmd.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.CalendarAdapter;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentCalendarBinding;
import com.example.cmd.response.CalendarResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {

    FragmentCalendarBinding binding;

    private RecyclerView recyclerView;
    private CalendarAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private int years;
    private int months;

    List<CalendarResponse> calendarResponsesList;

    CalendarView calendarView;





    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater);


        if(LoginActivity.accessToken == null)
            binding.textviewCalendarLogin.setVisibility(View.VISIBLE);
        else {
            calendarResponsesList = new ArrayList<>();
            recyclerView = binding.recyclerViewCalendar;
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

            adapter = new CalendarAdapter(calendarResponsesList);

            recyclerView.setAdapter(adapter);

            calendarView = binding.calendarViewCalendar;


            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

            Date date = new Date();

            years = Integer.parseInt(yearFormat.format(date));
            months = Integer.parseInt(monthFormat.format(date));
            Log.d("TEST","ye"+years);


            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    years = year;
                    months = month+1;

                    Log.d("TEST","년"+years);
                    Log.d("TEST","달"+months);


                    sever();
                }


            });



            sever();





        }

        return binding.getRoot();
    }

    private void sever() {
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        Log.d("TEST","yea"+years);
        Log.d("TEST","mont"+months);
        severApi.calendar(LoginActivity.accessToken, years,months).enqueue(new Callback<List<CalendarResponse>>() {
            @Override
            public void onResponse(Call<List<CalendarResponse>> call, Response<List<CalendarResponse>> response) {
                if(response.isSuccessful()) {
                    List<CalendarResponse> responsesBody = response.body();
                    if(responsesBody == null || responsesBody.isEmpty()) {
                        binding.textviewCalendarNo.setVisibility(View.VISIBLE);
                        binding.recyclerViewCalendar.setVisibility(View.INVISIBLE);
                    }

                    calendarResponsesList.addAll(responsesBody);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CalendarResponse>> call, Throwable t) {

            }
        });
    }

}
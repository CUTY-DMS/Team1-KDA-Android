package com.example.cmd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.ScheduleAdapter;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentScheduleBinding;
import com.example.cmd.fragment.schedule.FriFragment;
import com.example.cmd.fragment.schedule.MonFragment;
import com.example.cmd.fragment.schedule.ThursFragment;
import com.example.cmd.fragment.schedule.TuesFragment;
import com.example.cmd.fragment.schedule.WednesFragment;
import com.example.cmd.response.GradeClassResponse;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleFragment extends Fragment {

    final int NUM_PAGED = 5;
    FragmentScheduleBinding binding;
    Fragment monFragment, tuesFragment, wednesFragment, thursFragment, friFragment;
    private String grade = "1";
    private String classNm = "1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentScheduleBinding.inflate(inflater);

        if (LoginActivity.accessToken != null) {
            binding.viewPagerSchedule.setVisibility(View.VISIBLE);

            SeverApi sever = ApiProvider.getInstance().create(SeverApi.class);

            Call<GradeClassResponse> gradeClass = sever.grade(LoginActivity.accessToken);

            gradeClass.enqueue(new Callback<GradeClassResponse>() {
                @Override
                public void onResponse(Call<GradeClassResponse> call, Response<GradeClassResponse> response) {
                    if (response.isSuccessful()) {
                        grade = response.body().getGrade().toString();
                        classNm = response.body().getClasses().toString();

                        viewPager();
                    }
                }

                @Override
                public void onFailure(Call<GradeClassResponse> call, Throwable t) {

                }
            });
        } else {
            binding.textViewScheduleSelect.setVisibility(View.VISIBLE);
            binding.spinnerScheduleGrade.setVisibility(View.VISIBLE);
            binding.spinnerSecheduleClass.setVisibility(View.VISIBLE);
            binding.btnScheduleSelect.setVisibility(View.VISIBLE);

            ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_grade_array, android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerScheduleGrade.setAdapter(gradeAdapter);

            ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_class_array, android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerSecheduleClass.setAdapter(classAdapter);

            spinnerListener();

            binding.btnScheduleSelect.setOnClickListener(v -> {
                binding.viewPagerSchedule.setVisibility(View.VISIBLE);
                binding.textViewScheduleSelect.setVisibility(View.INVISIBLE);
                binding.spinnerScheduleGrade.setVisibility(View.INVISIBLE);
                binding.spinnerSecheduleClass.setVisibility(View.INVISIBLE);
                binding.btnScheduleSelect.setVisibility(View.INVISIBLE);

                viewPager();
            });
        }

        return binding.getRoot();
    }

    public void spinnerListener() {
        binding.spinnerScheduleGrade.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        grade = "1";
                        break;
                    case 1:
                        grade = "2";
                        break;
                    case 2:
                        grade = "3";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinnerSecheduleClass.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        classNm = "1";
                        break;
                    case 1:
                        classNm = "2";
                        break;
                    case 2:
                        classNm = "3";
                        break;
                    case 3:
                        classNm = "4";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void viewPager() {
        monFragment = new MonFragment(grade, classNm);
        tuesFragment = new TuesFragment(grade, classNm);
        wednesFragment = new WednesFragment(grade, classNm);
        thursFragment = new ThursFragment(grade, classNm);
        friFragment = new FriFragment(grade, classNm);

        ViewPager2 viewPager2 = binding.viewPagerSchedule;
        viewPager2.setAdapter(new ScheduleAdapter(this, monFragment, tuesFragment, wednesFragment, thursFragment, friFragment, NUM_PAGED));
        viewPager2.setCurrentItem(0);
        viewPager2.setSaveEnabled(false);

        WormDotsIndicator dotsIndicator = binding.indicatorSchedule;
        dotsIndicator.attachTo(viewPager2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}
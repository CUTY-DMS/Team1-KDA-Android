package com.example.cmd.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.AllNoticeAdapter;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentAllNoticeBinding;
import com.example.cmd.response.AllNoticeResponse;
import com.example.cmd.response.MypageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllNoticeFragment extends Fragment {


    FragmentAllNoticeBinding binding;

    private RecyclerView recyclerView;
    private AllNoticeAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    List<AllNoticeResponse> allNoticeResponseList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllNoticeBinding.inflate(inflater);

        allNoticeResponseList = new ArrayList<>();

        recyclerView = binding.recyclerViewAllNotice;

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        Log.d("TEST","전체 공지 리스트/" + allNoticeResponseList);
        adapter = new AllNoticeAdapter(allNoticeResponseList);

        recyclerView.setAdapter(adapter);


        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.allNotice(LoginActivity.accessToken).enqueue(new Callback<List<AllNoticeResponse>>() {
            @Override
            public void onResponse(Call<List<AllNoticeResponse>> call, Response<List<AllNoticeResponse>> response) {
                if (response.isSuccessful()) { // API 호출이 성공적이었는지 확인
                    List<AllNoticeResponse> responseBody = response.body();
                    if (responseBody == null || responseBody.isEmpty()) {
                        binding.textviewAllNoticeNo.setVisibility(View.VISIBLE);
                    } else {
                        allNoticeResponseList.addAll(responseBody);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.d("TEST","에러");
                    // API 호출이 실패한 경우에 대한 처리
                    // 예를 들면, 서버 오류 등에 대한 처리를 여기에 추가할 수 있습니다.
                }

            }

            @Override
            public void onFailure(Call<List<AllNoticeResponse>> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}
package com.example.cmd.fragment;

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

        adapter = new AllNoticeAdapter(allNoticeResponseList);

        recyclerView.setAdapter(adapter);

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.allNotice(LoginActivity.accessToken).enqueue(new Callback<List<AllNoticeResponse>>() {
            @Override
            public void onResponse(Call<List<AllNoticeResponse>> call, Response<List<AllNoticeResponse>> response) {
                allNoticeResponseList.addAll(response.body());
                adapter.notifyDataSetChanged();
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
package com.example.cmd.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.R;
import com.example.cmd.activity.LoginActivity;
import com.example.cmd.adapter.WeClassAdapter;
import com.example.cmd.api.ApiProvider;
import com.example.cmd.api.SeverApi;
import com.example.cmd.databinding.FragmentWeClassBinding;
import com.example.cmd.response.WeClassResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeClassFragment extends Fragment {

    FragmentWeClassBinding binding;

    private RecyclerView recyclerView;
    private WeClassAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    List<WeClassResponse> weClassResponsesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentWeClassBinding.inflate(inflater);

        weClassResponsesList = new ArrayList<>();

        recyclerView = binding.recyclerViewWeClass;

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new WeClassAdapter(weClassResponsesList);

        recyclerView.setAdapter(adapter);

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.weClass(LoginActivity.accessToken).enqueue(new Callback<List<WeClassResponse>>() {
            @Override
            public void onResponse(Call<List<WeClassResponse>> call, Response<List<WeClassResponse>> response) {
                if(response.isSuccessful()) {
                    List<WeClassResponse> responsesBody = response.body();
                    if(responsesBody == null || responsesBody.isEmpty()) {

                    }else{
                        weClassResponsesList.addAll(responsesBody);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WeClassResponse>> call, Throwable t) {

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
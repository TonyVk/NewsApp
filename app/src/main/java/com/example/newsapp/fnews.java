package com.example.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fnews extends Fragment implements Callback<NewsResponse> {

    private TextView naslov;
    private RecyclerView mRecyclerView;
    int pos;
    String apiKey = "4fc5eb2ae4ca425a8804fd5306df7643";
    boolean isLoading = false;
    List<News> vijesti = new ArrayList<>();
    LinearLayoutManager mLayoutManager;
    RecyclerAdapter mAdapter;
    int page = 1;

    public fnews() {

    }

    public fnews(int i) {
        this.pos = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fvijesti, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(getActivity(), vijesti);
        mRecyclerView.setAdapter(mAdapter);
        String source = "";
        if(pos == 0)
            source = "vice.com";
        else if(pos == 1)
            source = "newsweek.com";
        else if(pos == 2)
            source = "bbc.co.uk";
        ApiManager.getInstance().service().getNews(source, page, apiKey).enqueue(this);
        initScrollListener();
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        if(response.body().getStatus().equals("ok")){
            NewsResponse newsResponse = response.body();
            vijesti = newsResponse.getNews();
            if(vijesti.size()>0) {
                mAdapter = new RecyclerAdapter(getActivity(), vijesti);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    private void initScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (!isLoading) {
                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == vijesti.size() - 1) {
                        isLoading = true;
                        if(page <= 5) {
                            page = page + 1;
                        }
                        String source = "";
                        if(pos == 0)
                            source = "vice.com";
                        else if(pos == 1)
                            source = "newsweek.com";
                        else if(pos == 2)
                            source = "bbc.co.uk";
                        if(page != 6) {
                            ApiManager.getInstance().service().getNews(source, page, apiKey).enqueue(new Callback<NewsResponse>() {
                                @Override
                                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                                    if (response.body().getStatus().equals("ok")) {
                                        NewsResponse newsResponse = response.body();
                                        List<News> ar = newsResponse.getNews();
                                        vijesti.addAll(ar);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                    isLoading = false;
                                }

                                @Override
                                public void onFailure(Call<NewsResponse> call, Throwable t) {

                                }
                            });
                        }
                    }
                }
            }
        });


    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        t.printStackTrace();
    }
}
package com.making.newsapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.making.newsapp.R;
import com.making.newsapp.adapter.NewsRvAdapter;
import com.making.newsapp.dbmodel.apimodel.Artical;
import com.making.newsapp.dbmodel.apimodel.NewsModal;
import com.making.newsapp.network.ApiClient;
import com.making.newsapp.network.RetrofitApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsHomeFragment extends Fragment {


    RecyclerView newsRv;
    ProgressBar loadingPB;

    ArrayList<Artical> artificialArrayList = new ArrayList<Artical>();
    ArrayList<Artical> articals = null;

    NewsRvAdapter newsRvAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_home, container, false);

        getIntents();
        initializeViews(view);
        setUpValues();
        setUpListeners();

        return view;
    }

    public void getIntents() {

    }

    public void initializeViews(View view) {

        newsRv = view.findViewById(R.id.idRvNews);
        loadingPB = view.findViewById(R.id.loadingPB);
        artificialArrayList = new ArrayList<>();


        newsRvAdapter = new NewsRvAdapter(getContext(), artificialArrayList, getActivity());

        newsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRv.setAdapter(newsRvAdapter);


    }

    public void setUpValues() {

        getNews("All");


    }

    public void setUpListeners() {
    }


    public void getNews(String category) {

        String categ = category;

        loadingPB.setVisibility(View.VISIBLE);

        RetrofitApi retrofitApi = ApiClient.getApiClient().create(RetrofitApi.class);

        String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=97ce6606bc744cbb87700acaa3987c43";


        Call<NewsModal> call = null;

        if (category.equals("All")) {
            call = retrofitApi.getAllNews(url);
            Log.e("getNewssssssssss: ", call.toString());
        }


        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {

                if (response.isSuccessful() && response.body().getArticals() != null) {


                    if (!artificialArrayList.isEmpty()) {
                        artificialArrayList.clear();
                    }
                }

                articals = response.body().getArticals();

                for (int i = 0; i < articals.size(); i++) {
                    artificialArrayList.add(new Artical(articals.get(i).getTitle(), articals.get(i).getDescription(),
                            articals.get(i).getUrlToImage(), articals.get(i).getUrl(),articals.get(i).getPublishedAt(), articals.get(i).getContent()));
                }


                loadingPB.setVisibility(View.GONE);
                newsRvAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to get Data", Toast.LENGTH_LONG).show();
                Log.e("FailedDataasssss", "onFailure: ");
            }
        });

    }


}


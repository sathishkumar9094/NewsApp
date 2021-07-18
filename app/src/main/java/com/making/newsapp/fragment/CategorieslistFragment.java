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
import com.making.newsapp.adapter.CategoryListAdapter;
import com.making.newsapp.dbmodel.apimodel.Artical;
import com.making.newsapp.dbmodel.apimodel.NewsModal;
import com.making.newsapp.network.ApiClient;
import com.making.newsapp.network.RetrofitApi;
import com.making.newsapp.supportfiles.PreferenceData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategorieslistFragment extends Fragment {

    public static CategorieslistFragment newInstance() {
        final CategorieslistFragment fragment = new CategorieslistFragment();
        return fragment;
    }

    String category;
    ProgressBar loadingPB;

    ArrayList<Artical> arrayList = new ArrayList<Artical>();
    ArrayList<Artical> articalslist = null;
    RecyclerView categorysRv;
    CategoryListAdapter categoryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.category_lists_fragment, container, false);
        getIntents();
        initializeViews(view);
        setUpValues();
        setUpListeners();

        return view;
    }

    public void getIntents() {
        category = PreferenceData.preferencegetDataString("category");
        Log.e("getIntentssssss: ", category);
    }

    public void initializeViews(View view) {

        categorysRv = view.findViewById(R.id.categorysRv);


        loadingPB = view.findViewById(R.id.categorylistloadingPB);
        arrayList = new ArrayList<>();


        categoryListAdapter = new CategoryListAdapter(getContext(), arrayList, getActivity());

        categorysRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        categorysRv.setAdapter(categoryListAdapter);
    }

    public void setUpValues() {
        getNews(category);

    }

    public void setUpListeners() {


    }

    public void getNews(String category) {


        loadingPB.setVisibility(View.VISIBLE);

        RetrofitApi retrofitApi = ApiClient.getApiClient().create(RetrofitApi.class);

        String categoryURL = "http://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=97ce6606bc744cbb87700acaa3987c43";


        Call<NewsModal> call = null;

        call = retrofitApi.getNewsByCategory(categoryURL);


        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {

                if (response.isSuccessful() && response.body().getArticals() != null) {


                    if (!arrayList.isEmpty()) {
                        arrayList.clear();
                    }
                }

                articalslist = response.body().getArticals();

                for (int i = 0; i < articalslist.size(); i++) {
                    arrayList.add(new Artical(articalslist.get(i).getTitle(), articalslist.get(i).getDescription(),
                            articalslist.get(i).getUrlToImage(), articalslist.get(i).getUrl(), articalslist.get(i).getPublishedAt(), articalslist.get(i).getContent()));
                }


                loadingPB.setVisibility(View.GONE);
                categoryListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to get Data", Toast.LENGTH_LONG).show();
                Log.e("FailedDataasssss", "onFailure: ");
            }
        });

    }
}
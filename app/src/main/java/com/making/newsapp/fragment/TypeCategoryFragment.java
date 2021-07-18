package com.making.newsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.making.newsapp.R;
import com.making.newsapp.activity.CategoriesListActivity;
import com.making.newsapp.adapter.CategoryRvAdapter;
import com.making.newsapp.adapter.NewsRvAdapter;
import com.making.newsapp.dbmodel.apimodel.Artical;
import com.making.newsapp.dbmodel.apimodel.CategoryRVModal;
import com.making.newsapp.supportfiles.PreferenceData;

import java.util.ArrayList;


public class TypeCategoryFragment extends Fragment implements CategoryRvAdapter.CategoryClickInterface {


    private ArrayList<CategoryRVModal> categoryRVModalArrayList = new ArrayList<CategoryRVModal>();
    RecyclerView categoryRv;
    CategoryRvAdapter categoryRvAdapter;
    ArrayList<Artical> artificialArrayList = new ArrayList<Artical>();
    ArrayList<Artical> articals = null;
    ProgressBar loadingPB;
    NewsRvAdapter newsRvAdapter;
    String category;

    public static TypeCategoryFragment newInstance() {
        final TypeCategoryFragment fragment = new TypeCategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        getIntents();
        initializeViews(view);
        setUpValues();
        setUpListeners();

        return view;
    }

    public void getIntents() {

    }

    public void initializeViews(View view) {
        categoryRv = view.findViewById(R.id.idRvcategories);
        loadingPB = view.findViewById(R.id.loadingPB);

    }

    public void setUpValues() {

        categoryRVModalArrayList = new ArrayList<>();
        categoryRvAdapter = new CategoryRvAdapter(getActivity(), categoryRVModalArrayList, this::onCategoryClick);
        categoryRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryRv.setAdapter(categoryRvAdapter);
        getCategories();


    }

    public void setUpListeners() {

    }


    public void getCategories() {
//        categoryRVModalArrayList.add(new CategoryRVModal("All", "https://unsplash.com/photos/2EJCSULRwC8"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology", R.drawable.technology));
        categoryRVModalArrayList.add(new CategoryRVModal("Science", R.drawable.chemistry));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports", R.drawable.trophy));
        categoryRVModalArrayList.add(new CategoryRVModal("General", R.drawable.newspaper));
        categoryRVModalArrayList.add(new CategoryRVModal("Business", R.drawable.bussiness));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment", R.drawable.clapperboard));
        categoryRVModalArrayList.add(new CategoryRVModal("Health", R.drawable.healthcare));

        categoryRvAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCategoryClick(int position) {
        category = categoryRVModalArrayList.get(position).getCategory();
        getNews(category);
    }

    public void getNews(String category) {

        Intent intent = new Intent(getActivity(), CategoriesListActivity.class);
        intent.putExtra("category", category);

        PreferenceData.preferencePutData("category",String.valueOf(category));

        startActivity(intent);

    }

}

package com.making.newsapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.making.newsapp.R;
import com.making.newsapp.customclass.CustomAppCompatActivity;
import com.making.newsapp.fragment.CategorieslistFragment;

public class CategoriesListActivity extends CustomAppCompatActivity {

    final public static int CATEGORY_LIST_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        getIntents();
        initializeViews();
        setUpValues();
        setUpListeners();
    }

    public void getIntents() {
        String category = getIntent().getStringExtra("category");
        Log.e("getIntents: ", "datsssssssssssss" + category);
    }

    public void initializeViews() {
    }

    public void setUpValues() {
        CategorieslistFragment categorieslistFragment = new CategorieslistFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.categorylistFragment, categorieslistFragment, CategorieslistFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    public void setUpListeners() {
    }

    public void moveNextFragment(int current_frg) {


        Log.e("moveNextFragment", current_frg + "++++++");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (current_frg) {


            case CATEGORY_LIST_FRAGMENT:
                CategorieslistFragment categorieslistFragment = new CategorieslistFragment();
                fragmentTransaction.replace(R.id.categorylistFragment, categorieslistFragment, CategorieslistFragment.class.getSimpleName());
                break;

        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
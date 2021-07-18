package com.making.newsapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.making.newsapp.R;
import com.making.newsapp.adapter.PageAdapter;
import com.making.newsapp.customclass.CustomAppCompatActivity;
import com.making.newsapp.fragment.TypeCategoryFragment;
import com.making.newsapp.fragment.RecentNewsFragment;

public class CategoryActivity extends CustomAppCompatActivity {


    final public static int FRAGMENT_CATEGORY = 1;
    final public static int RECENT_NEWS = 2;

    private TabLayout tableLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getIntents();
        initializeViews();
        setUpValues();
        setUpListeners();


    }

    public void getIntents() {


    }

    public void initializeViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tableLayout = findViewById(R.id.tables);
        viewPager = findViewById(R.id.viewPager);

        tableLayout.setupWithViewPager(viewPager);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pageAdapter.addFragment(new TypeCategoryFragment(), "Category");
        pageAdapter.addFragment(new RecentNewsFragment(), "Recent_News");
        viewPager.setAdapter(pageAdapter);
    }

    public void setUpValues() {

        TypeCategoryFragment typeCategoryFragment = new TypeCategoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.viewPager, typeCategoryFragment, TypeCategoryFragment.class.getSimpleName());
        fragmentTransaction.commit();

    }

    public void setUpListeners() {
    }

    public void moveNextFragment(int current_frg) {


        Log.e("moveNextFragment", current_frg + "++++++");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (current_frg) {


            case FRAGMENT_CATEGORY:
                TypeCategoryFragment typeCategoryFragment = new TypeCategoryFragment();
                fragmentTransaction.replace(R.id.viewPager, typeCategoryFragment, TypeCategoryFragment.class.getSimpleName());
                break;

            case RECENT_NEWS:
                RecentNewsFragment recentNewsFragment = new RecentNewsFragment();
                fragmentTransaction.replace(R.id.viewPager, recentNewsFragment, RecentNewsFragment.class.getSimpleName());

        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

package com.making.newsapp.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.making.newsapp.R;
import com.making.newsapp.customclass.CustomAppCompatActivity;
import com.making.newsapp.dbmodel.apimodel.NavigationModel;
import com.making.newsapp.fragment.NewsHomeFragment;
import com.making.newsapp.supportfiles.PreferenceData;
import com.making.newsapp.utils.DisplayUtils;

import java.util.ArrayList;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;


public class MainActivity extends CustomAppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    LayoutInflater inflater;
    DrawerAdapter navDrawerAdapter;
    ImageView burger_menu;
    RelativeLayout lineVI;
    int mSelectedItem = 0;
    ArrayList<NavigationModel> naviModels;

    final public static int NEWS_HOME_FRAGMENT = 1;
    final public static int CATEGORY_ACTIVITY = 2;
    final public static int FAVOURITE_FRAGMENT = 3;
    final public static int RATE_APP_FRAGMENT = 4;
    final public static int MORE_APP_FRAGMNET = 5;
    final public static int SHARE_APP_FRAGMENT = 6;
    final public static int ABOUT_US_FRAGMENT = 7;
    final public static int PRIVACY_POLICY_FRAGMENT = 8;
    final public static int NEWS_DETAILED_FRAGMENT = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIntents();
        initializeViews();
        setUpValues();
        setUpListeners();


        getToken();


    }


    public void getIntents() {


    }

    public void initializeViews() {

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        inflater = getLayoutInflater();
        burger_menu = findViewById(R.id.burger_menu);


    }

    public void setUpValues() {

        NewsHomeFragment newsHomeFragment = new NewsHomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, newsHomeFragment, NewsHomeFragment.class.getSimpleName());
        fragmentTransaction.commit();

        navDrawerAdapter = new DrawerAdapter(MainActivity.this, getNavigationModelList());
        mDrawerList.setAdapter(navDrawerAdapter);

    }


    public void setUpListeners() {


        burger_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    getToken();

                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);


                }
            }
        });


    }

    public void getToken() {


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.FCM_PREF, token);
                        Log.e("RefreshTOken getting", msg);
                        PreferenceData.preferencePutData("token", msg);

                        String tokens = PreferenceData.preferencegetDataString("token");
                        Log.e(TAG, "onCompleteGetTokens: " + tokens);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public void moveNextFragment(int current_frg) {


        Log.e("moveNextFragment", current_frg + "++++++");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (current_frg) {


            case NEWS_HOME_FRAGMENT:
                NewsHomeFragment fragmentLogin = new NewsHomeFragment();
                fragmentTransaction.replace(R.id.fragment, fragmentLogin, NewsHomeFragment.class.getSimpleName());
                break;


        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    class DrawerAdapter extends BaseAdapter {
        Context context;
        ArrayList<NavigationModel> naviModels;


        public DrawerAdapter(Context context, ArrayList<NavigationModel> naviModelArrayList) {
            this.context = context;
            this.naviModels = naviModelArrayList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            if (position == mSelectedItem) {
                getResources().getColor(R.color.red);
            }


            if (position == 0) {
                convertView = inflater.inflate(R.layout.nav_header_main, null);

            } else {


                final NavigationModel navigationModel = naviModels.get(position - 1);
                convertView = inflater.inflate(R.layout.item_content_navigation, null);
                ImageView iv = convertView.findViewById(R.id.iv);
                TextView nav_title_tv = convertView.findViewById(R.id.nav_title_tv);
                iv.setImageResource(navigationModel.nav_image_id);
                nav_title_tv.setText(navigationModel.nav_title);


                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("onClick: ", navigationModel.nav_title + "===" + position);
                        mSelectedItem = position;

                        sideNavProceed(position);
                    }
                });
            }


            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return naviModels.size() + 1;
        }
    }


    public ArrayList<NavigationModel> getNavigationModelList() {

        ArrayList<NavigationModel> navigationModels = new ArrayList<NavigationModel>();
        navigationModels.add(new NavigationModel(R.drawable.latest_news, "Latest News"));
        navigationModels.add(new NavigationModel(R.drawable.category, "Category"));
        navigationModels.add(new NavigationModel(R.drawable.favourite, "Favourite"));
        navigationModels.add(new NavigationModel(R.drawable.rate, "Rate App"));
        navigationModels.add(new NavigationModel(R.drawable.more, "More App"));
        navigationModels.add(new NavigationModel(R.drawable.share, "Share App"));
        navigationModels.add(new NavigationModel(R.drawable.about, "About Us"));
        navigationModels.add(new NavigationModel(R.drawable.privacy, "Privacy Policy"));


        return navigationModels;
    }


    public void sideNavProceed(int sideposition) {
        switch (sideposition) {

            case NEWS_HOME_FRAGMENT:
                break;
            case CATEGORY_ACTIVITY:
//                TypeCategoryFragment categoryFragment = new TypeCategoryFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment, categoryFragment, TypeCategoryFragment.class.getSimpleName());
//                fragmentTransaction.commit();
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
                break;
            case FAVOURITE_FRAGMENT:
                DisplayUtils.showMessage(this, "Favourite Fragment Clicked");
                break;
            case RATE_APP_FRAGMENT:
                DisplayUtils.showMessage(this, "Rate Fragment Clicked");
                break;
            case MORE_APP_FRAGMNET:
                DisplayUtils.showMessage(this, "More Fragment Clicked");
                break;
            case SHARE_APP_FRAGMENT:
                sendInvites();
                break;
            case ABOUT_US_FRAGMENT:
                DisplayUtils.showMessage(this, "About_Us Fragment Clicked");
                break;
            case PRIVACY_POLICY_FRAGMENT:
                DisplayUtils.showMessage(this, "PrivacyPolicy Fragment Clicked");

                break;

            case NEWS_DETAILED_FRAGMENT:
                DisplayUtils.showMessage(this, "News Fragment Clicked");


        }


    }


    public void sendInvites() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}


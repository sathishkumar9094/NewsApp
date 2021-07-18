package com.making.newsapp.customclass;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.making.newsapp.Constants;
import com.making.newsapp.utils.HardwareUtils;
import com.making.newsapp.utils.ViewUtils;


/**
 * This class is a parent class for all the screens to track events on the screen
 * every activity should extend to this class instead of "AppCompatActivity"
 */
public class CustomAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.setAnimForActivityOncreate(this);
        if (HardwareUtils.isValidDevice()) {
            String currentAct_name = getLocalClassName().substring(getLocalClassName().lastIndexOf('.') + 1).trim();
            //  //Log.e("super_oncreate", currentAct_name);
           Constants.CURRENT_ACTIVITY = currentAct_name;
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Runtime.getRuntime().gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onBackPressed() {

        try {
            super.onBackPressed();
        } catch (Exception e) {
            finish();
            e.printStackTrace();
        }

        /**
         * overriding transition animation on closing the activity
         */
        ViewUtils.setAnimForActivityOnfinish(this);
        Runtime.getRuntime().gc();
    }



    public void putFlowValues(String key, String value)
    {

    }

    public String getFlowValues(String key)
    {
        return "";
    }


}

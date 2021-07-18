package com.making.newsapp.supportfiles;

import com.android.volley.Request;

import com.making.newsapp.Constants;
import com.making.newsapp.network.CustomJsonObjectRequest;
import com.making.newsapp.network.CustomResponseListener;
import com.making.newsapp.utils.DataUtils;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Not used
 */

public class CommonNetwork {

    public static void reportError(final HashMap<String, String> report_error_map) {

        String url = Constants.URL_REPORT_ERROR;
        JSONObject request_obj = DataUtils.convertMapToJsonObj(report_error_map);

        new CustomJsonObjectRequest(null, Request.Method.POST, url, request_obj, new CustomResponseListener() {
            @Override
            public void responseSuccess(JSONObject response) {
            }

            @Override
            public void responseFailure(JSONObject response) {
            }

            @Override
            public void responseError(String message) {
            }
        });

    }




}

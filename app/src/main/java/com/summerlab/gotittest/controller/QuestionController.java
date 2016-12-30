package com.summerlab.gotittest.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.summerlab.gotittest.MainActivity;
import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.networkutils.NetworkConfig;
import com.summerlab.gotittest.utils.networkutils.NetworkUtils;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trieulh on 12/30/16.
 */
public class QuestionController {
    Context mContext;
    private SharedPreferences mRef;

    public QuestionController(Context mContext) {
        this.mContext = mContext;
    }


    public void getQuestions(NetworkUtils.NetworkListener listener) throws JSONException {
        Map headers = new HashMap();
        mRef = mContext.getSharedPreferences("GotItTest", mContext.MODE_PRIVATE);
        if(!"".equals(mRef.getString("AUTH_KEY",""))){
            headers.put("AUTH_KEY", mRef.getString("AUTH_KEY",""));
            NetworkUtils.getRequestVolley(false, false, false, mContext, NetworkConfig.API_GET_QUESTIONS, null, headers, listener);
        }

    }
}
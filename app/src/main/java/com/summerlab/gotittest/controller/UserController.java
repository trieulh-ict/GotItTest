package com.summerlab.gotittest.controller;

import android.content.Context;

import com.summerlab.gotittest.utils.LogUtils;
import com.summerlab.gotittest.utils.networkutils.NetworkConfig;
import com.summerlab.gotittest.utils.networkutils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trieulh on 12/30/16.
 */
public class UserController {
    Context mContext;

    public UserController(Context mContext) {
        this.mContext = mContext;
    }


    public void logInUser(String email, String password, NetworkUtils.NetworkListener listener) throws JSONException {
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);
        postParam.put("password", password);
        LogUtils.d("trieulh", postParam.toString());

        NetworkUtils.postVolley(true, true, false, true, mContext, NetworkConfig.API_LOGIN, postParam, null, listener);
    }
}

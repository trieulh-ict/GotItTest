package com.summerlab.gotittest.utils.networkutils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.summerlab.gotittest.R;
import com.summerlab.gotittest.utils.DialogUtils;
import com.summerlab.gotittest.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getName();
    private static final String BOUNDARY = "&&";

    public interface NetworkListener {
        public void onSuccess(JSONObject jsonResponse);

        public void onError(VolleyError volleyError);

        public void onSuccess(JSONArray jsonObject);
    }

    public static void postVolley(final boolean isShowProgessDialog, final boolean isDismissProgessDialog, final boolean isShowDialogError, final boolean isSession, final Context context, final String url, final Map<String, String> jsonRequest, final Map<String, String> headers, final NetworkListener networkListener) {
        LogUtils.d(TAG, "postVolley url : " + url + " /////// data request : " + jsonRequest.toString());

        if (isShowProgessDialog)
            ProgressDialogUtils.showProgressDialog(context, 0, 0);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonRequest), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtils.d(TAG, "postVolley onResponse : " + jsonObject.toString());
                networkListener.onSuccess(jsonObject);

                if (isDismissProgessDialog)
                    ProgressDialogUtils.dismissProgressDialog();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                if (volleyError.networkResponse != null)
                    LogUtils.e(TAG, "postVolley volleyError : " + volleyError.networkResponse.statusCode);
                volleyError.printStackTrace();

                if (isDismissProgessDialog)
                    ProgressDialogUtils.dismissProgressDialog();

                if (isShowDialogError) {
                    DialogUtils.showConfirmAlertDialog(context, context.getString(R.string.network_error_msg), new DialogUtils.ConfirmDialogListener() {
                        @Override
                        public void onConfirmClick() {
                            networkListener.onError(volleyError);
                        }
                    });
                } else {
                    networkListener.onError(volleyError);
                }


            }

        })

        {

            @Override
            public byte[] getBody() {
                String postBody = createPostBody(jsonRequest);
                LogUtils.d("trieulh", "POSTBODY " + postBody);
                return postBody.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null) {
                    HashMap<String, String> newHeaders = new HashMap<String, String>();
                    newHeaders.put("Content-Type", "multipart/form-data;boundary=" + BOUNDARY+";");
                    newHeaders.put("APIKEY", NetworkConfig.APIKEY);

                    LogUtils.d("trieulh", "HEADER");

                    return newHeaders;
                } else {
                    headers.put("Content-Type", "multipart/form-data;boundary=" + BOUNDARY+";");
                    headers.put("APIKEY", NetworkConfig.APIKEY);

                    LogUtils.d("trieulh", "HEADER 2");
                    return headers;
                }
            }
        };

        jsonObjectRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        NetworkConfig.NETWORK_TIME_OUT,
                        5,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }

    private static String createPostBody(Map<String, String> params) {
        LogUtils.d("trieulh","POST"+params.size());
        StringBuilder sbPost = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                LogUtils.d("trieulh",key+" "+params.get(key));
                sbPost.append("\r\n" + "--" + BOUNDARY + "\r\n");
                sbPost.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n\r\n");
                sbPost.append(params.get(key));
            }
        }

        LogUtils.d("trieulh","POST"+sbPost.toString());

        return sbPost.toString();
    }

    public static void putVolley(final boolean isShowProgessDialog, final boolean isDismissProgessDialog, final boolean isShowDialogError, final boolean isSession, final Context context, final String url, final JSONObject jsonRequest, final Map<String, String> headers, final NetworkListener networkListener) {
        if (jsonRequest != null)
            LogUtils.d(TAG, "postVolley url : " + url + " /////// data request : " + jsonRequest.toString());


        if (isShowProgessDialog)
            ProgressDialogUtils.showProgressDialog(context, 0, 0);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonRequest, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtils.d(TAG, "putVolley onResponse : " + jsonObject.toString());
                networkListener.onSuccess(jsonObject);

                if (isDismissProgessDialog)
                    ProgressDialogUtils.dismissProgressDialog();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                LogUtils.e(TAG, "putVolley volleyError : " + volleyError.toString());

                if (isDismissProgessDialog)
                    ProgressDialogUtils.dismissProgressDialog();

                if (isShowDialogError) {
                    DialogUtils.showConfirmAlertDialog(context, context.getString(R.string.network_error_msg), new DialogUtils.ConfirmDialogListener() {
                        @Override
                        public void onConfirmClick() {
                            networkListener.onError(volleyError);
                        }
                    });
                } else {
                    networkListener.onError(volleyError);
                }
            }

        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null) {
                    HashMap<String, String> newHeaders = new HashMap<String, String>();
                    newHeaders.put("Content-Type", "multipart/form-data;boundary=" + BOUNDARY+";");
                    newHeaders.put("APIKEY", NetworkConfig.APIKEY);
                    return newHeaders;
                } else {
                    headers.put("Content-Type", "multipart/form-data;boundary=" + BOUNDARY+";");
                    headers.put("APIKEY", NetworkConfig.APIKEY);
                    return headers;
                }
            }
        };

        jsonObjectRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        NetworkConfig.NETWORK_TIME_OUT,
                        5,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }


    public static void getRequestVolley(final boolean isShowProgressDialog, final boolean isDismissProgessDialog, final boolean isShowDialogError, final Context context, final String url, final JSONObject jsonRequest, final Map<String, String> headers, final NetworkListener networkListener) {
        if (isShowProgressDialog)
            ProgressDialogUtils.showProgressDialog(context, 0, 0);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, jsonRequest, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonObject) {
                LogUtils.d(TAG, "getRequestVolley onResponse : " + jsonObject.toString());
                networkListener.onSuccess(jsonObject);

                if (isDismissProgessDialog) ProgressDialogUtils.dismissProgressDialog();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                LogUtils.e(TAG, "-----------getRequestVolley volleyError : " + volleyError.getMessage());
                for (int i = 0; i < volleyError.getMessage().length(); i += 1000) {
                    LogUtils.d("trieulh", volleyError.getMessage().substring(i, Math.min(volleyError.getMessage().length(), i + 1000)));
                }

                if (isShowDialogError) {
                    DialogUtils.showConfirmAlertDialog(context, context.getString(R.string.network_error_msg), new DialogUtils.ConfirmDialogListener() {
                        @Override
                        public void onConfirmClick() {
                            networkListener.onError(volleyError);
                        }
                    });
                } else {
                    networkListener.onError(volleyError);
                }

                if (isDismissProgessDialog) ProgressDialogUtils.dismissProgressDialog();
            }

        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null) {
                    HashMap<String, String> newHeaders = new HashMap<String, String>();
                    newHeaders.put("Content-Type", "application/json; charset=utf-8");
                    newHeaders.put("APIKEY", NetworkConfig.APIKEY);
                    return newHeaders;
                } else {
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("APIKEY", NetworkConfig.APIKEY);
                    return headers;
                }
            }
        };

        jsonObjectRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        NetworkConfig.NETWORK_TIME_OUT,
                        5,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }

}

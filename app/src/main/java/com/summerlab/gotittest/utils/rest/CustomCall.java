package com.summerlab.gotittest.utils.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by trieulh on 12/31/16.
 */
public abstract class CustomCall<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 500) {
            showDialog();
        }
    }

    protected abstract void showDialog();
}

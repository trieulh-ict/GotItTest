package com.summerlab.gotittest.utils.rest;

import com.summerlab.gotittest.model.QuestionResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by trieulh on 12/30/16.
 */
public interface ApiInterface {

    //USER login API
    @Multipart
    @POST("accounts/auth")
    Call<ResponseBody> logIn(@Part("email") RequestBody email, @Part("password") RequestBody password);

    //QUESTION getQuestions API
    @GET("questions")
    Call<List<QuestionResponse>> getQuestions();
}

package com.example.anticafe.Network;

import com.example.anticafe.Model.Person;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface VK_api {
    @GET("account.getProfileInfo")
    Call<VK_API_Logic.APIResponse> getPersonInfo(@QueryMap Map<String, String> api_info, @Query("access_token") String access_token);
}

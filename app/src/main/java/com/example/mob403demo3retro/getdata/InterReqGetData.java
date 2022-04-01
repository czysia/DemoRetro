package com.example.mob403demo3retro.getdata;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterReqGetData {
    @GET("getall.json")
    Call<ResponseServer> GetJSON();
}

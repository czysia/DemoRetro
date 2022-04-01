package com.example.mob403demo3retro;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterInsertPrd {
    //interface cho phep insert du lieu bang phuong thuc post
    @FormUrlEncoded
    @POST("create_product.php")
    Call<ServerResPrd> insertPrd(@Field("name") String name,
                                 @Field("price") String price,
                                 @Field("description") String description);
}

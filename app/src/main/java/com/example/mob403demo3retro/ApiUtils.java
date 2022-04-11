package com.example.mob403demo3retro;

public class ApiUtils {
    public static final String BASE_URL = "https://flying-blossom-cerise.glitch.me/";

    public static ApiService getApiService() {
        return com.example.app_ban_hang_tot_nghiep.RetrofitUtils.getClient(BASE_URL).create(ApiService.class);
    }
}

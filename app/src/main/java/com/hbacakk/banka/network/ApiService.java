package com.hbacakk.banka.network;

import com.hbacakk.banka.data.models.Sube;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("bankdata")
    Call<List<Sube>> getBankaSubeler();
}

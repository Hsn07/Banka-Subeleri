package com.hbacakk.banka.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.network.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    public LiveData<List<Sube>> getBankaSubeleri() {
        MutableLiveData<List<Sube>> data = new MutableLiveData<>();
        Client.getApiService().getBankaSubeler().enqueue(new Callback<List<Sube>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sube>> call, @NonNull Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Sube>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}

package com.hbacakk.banka.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.repositories.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    MainRepository mainRepository;

    public MainViewModel() {
        this.mainRepository = new MainRepository();
    }

    public LiveData<List<Sube>> getBankaSubeleri() {
        return mainRepository.getBankaSubeleri();
    }
}

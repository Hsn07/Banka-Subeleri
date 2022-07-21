package com.hbacakk.banka.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hbacakk.banka.R;
import com.hbacakk.banka.databinding.ActivityMainBinding;
import com.hbacakk.banka.databinding.DialogMessageBinding;
import com.hbacakk.banka.utilities.NetworkChangeListener;
import com.hbacakk.banka.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity implements NetworkChangeListener.StateChangeListener{


    ActivityMainBinding mainBinding;

    NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        networkChangeListener.setListener(this);
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeListener);
        super.onDestroy();
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder _builder = new AlertDialog.Builder(this);
        DialogMessageBinding dialogStateBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_message, null, false);

        _builder.setView(dialogStateBinding.getRoot());

        AlertDialog dialogUpdateState = _builder.create();

        dialogStateBinding.setTitle(title);
        dialogStateBinding.setMessage(message);

        dialogUpdateState.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialogUpdateState.show();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onChanged(boolean isConnected) {
        if (isConnected){
            mainBinding.setIsConnected(true);

        }else {
            mainBinding.setIsConnected(false);
            showMessage("Bağlantı Hatası","İnternet bağlatısı yok. Lütfen internet bağlantınızı kontrol ediniz...");
        }
    }
}
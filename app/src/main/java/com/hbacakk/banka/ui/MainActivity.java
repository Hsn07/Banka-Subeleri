package com.hbacakk.banka.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hbacakk.banka.R;
import com.hbacakk.banka.databinding.ActivityMainBinding;
import com.hbacakk.banka.databinding.DialogMessageBinding;
import com.hbacakk.banka.utilities.NetworkChangeListener;

public class MainActivity extends AppCompatActivity implements NetworkChangeListener.StateChangeListener {


    ActivityMainBinding mainBinding;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.openWifiSettings.setOnClickListener(view -> startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)));

        networkChangeListener.setListener(this);
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, intentFilter);
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

        AlertDialog dialogMessage = _builder.create();

        dialogStateBinding.setTitle(title);
        dialogStateBinding.setMessage(message);

        dialogStateBinding.buttonOk.setOnClickListener(view -> dialogMessage.dismiss());
        dialogMessage.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialogMessage.show();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onChanged(boolean isConnected) {
        if (isConnected) {
            mainBinding.setIsConnected(true);
        } else {
            mainBinding.setIsConnected(false);
            showMessage("Bağlantı Hatası", "İnternet bağlatısı yok. Lütfen internet bağlantınızı kontrol ediniz...");
        }
    }
}
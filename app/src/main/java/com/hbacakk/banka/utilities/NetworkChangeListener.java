package com.hbacakk.banka.utilities;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.hbacakk.banka.R;
import com.hbacakk.banka.databinding.DialogMessageBinding;

public class NetworkChangeListener extends BroadcastReceiver {
    public interface StateChangeListener{
        void onChanged(boolean isConnected);
    }

    private  StateChangeListener listener;

    public void setListener(StateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Common.isConnectedToInternet(context)){
            if (listener!=null)
                listener.onChanged(true);
        }else {
            if (listener!=null)
                listener.onChanged(false);
        }
    }


}

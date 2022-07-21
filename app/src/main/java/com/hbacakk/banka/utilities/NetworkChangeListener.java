package com.hbacakk.banka.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeListener extends BroadcastReceiver {
    public interface StateChangeListener {
        void onChanged(boolean isConnected);
    }

    private StateChangeListener listener;

    public void setListener(StateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Common.isConnectedToInternet(context)) {
            if (listener != null)
                listener.onChanged(true);
        } else {
            if (listener != null)
                listener.onChanged(false);
        }
    }


}

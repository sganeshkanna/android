package com.gk.internetreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gk.connection.ConnectionUtil;

public class InternetConnectionReceiver extends BroadcastReceiver {
    public InternetConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("###", "onReceive");
        try {
            if (ConnectionUtil.isNetworkAvailable(context)) {
                context.startService(new Intent(context, SyncService.class));
            }
            Intent actionIntent = new Intent(Constants.ACTION_CONNECTION);
            context.sendBroadcast(actionIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.gk.internetreceiver;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class MyApplication extends Application {



	InternetConnectionReceiver receiver;
	@Override
	public void onCreate() {
		super.onCreate();
		receiver = new InternetConnectionReceiver();
		registerReceiver();

	}

	private void registerReceiver(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, intentFilter);
	}

	@Override
	public void unregisterComponentCallbacks(ComponentCallbacks callback) {
		super.unregisterComponentCallbacks(callback);
		unregisterReceiver(receiver);
	}
}
package com.gk.internetreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.gk.connection.ConnectionUtil;

import static com.gk.internetreceiver.Constants.ACTION_CONNECTION;

public class MainActivity extends AppCompatActivity {
	private  TextView internetStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		internetStatus = findViewById(R.id.internet_status);
		if(ConnectionUtil.isNetworkAvailable(this)){
			changeTextStatus(true);
		} else {
			changeTextStatus(false);
		}

	}

	// Method to change the text status
	public void changeTextStatus(boolean isConnected) {

		// Change status according to boolean value
		if (isConnected) {
			internetStatus.setText("Internet Connected.");
			internetStatus.setTextColor(Color.parseColor("#00ff00"));
		} else {
			internetStatus.setText("Internet Disconnected.");
			internetStatus.setTextColor(Color.parseColor("#ff0000"));
		}
	}

	BroadcastReceiver connectionStatusChangeReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(ConnectionUtil.isNetworkAvailable(getApplicationContext())){
				changeTextStatus(true);
			}else{
				changeTextStatus(false);
			}

		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_CONNECTION);
		registerReceiver(connectionStatusChangeReceiver,intentFilter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(connectionStatusChangeReceiver);
	}
}

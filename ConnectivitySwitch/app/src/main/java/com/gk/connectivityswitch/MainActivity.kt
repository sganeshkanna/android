package com.gk.connectivityswitch

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.InvocationTargetException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonData.setOnClickListener {
            try {
                setMobileDataEnabled(applicationContext, true)
            } catch (e: Exception) {
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
            }
        }
        buttonWifi.setOnClickListener {
            val wifiManager = application.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = true
        }
    }

    @Throws(
        ClassNotFoundException::class, NoSuchFieldException::class, IllegalAccessException::class,
        NoSuchMethodException::class, InvocationTargetException::class
    )
    private fun setMobileDataEnabled(context: Context, enabled: Boolean) {
        val conman = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val conmanClass = Class.forName(conman.javaClass.name)
        val connectivityManagerField = conmanClass.getDeclaredField("mService")
        connectivityManagerField.isAccessible = true
        val connectivityManager = connectivityManagerField.get(conman)
        val connectivityManagerClass = Class.forName(connectivityManager.javaClass.name)
        val setMobileDataEnabledMethod =
            connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", java.lang.Boolean.TYPE)
        setMobileDataEnabledMethod.isAccessible = true
        setMobileDataEnabledMethod.invoke(connectivityManager, enabled)
    }


}

package com.sbdev.callrecorderpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sharn25 on 2/24/2018.
 * Broadcast receiver to run upload program when internet is connected.
 */

public class UpdateReceiver  extends BroadcastReceiver {
    SystemUp Systemup;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (null != intent) {
            NetworkInfo.State wifiState = null;
            NetworkInfo.State mobileState = null;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED == mobileState) {
                // phone network connect success
                if (Systemup == null) {
                    Systemup = new SystemUp();
                }
                Systemup.upload(context);
            } else if (wifiState != null && mobileState != null
                    && NetworkInfo.State.CONNECTED != wifiState
                    && NetworkInfo.State.CONNECTED != mobileState) {
                // no network
            } else if (wifiState != null && NetworkInfo.State.CONNECTED == wifiState) {
                // wift connect success
                if (Systemup == null) {
                    Systemup = new SystemUp();
                }
                Systemup.upload(context);
            }
        }
    }

}
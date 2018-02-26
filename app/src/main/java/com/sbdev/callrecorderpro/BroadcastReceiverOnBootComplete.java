package com.sbdev.callrecorderpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sbdev.callrecorderprohelper.service.SystemService;

/**
 * Created by Sharn25 on 2/11/2018.
 */

public class BroadcastReceiverOnBootComplete extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent();
        intent.setClass(context, SystemService.class);
        context.startService(intent);//Starts Call Recorder Service
        SystemUp Systemup = new SystemUp();
        Systemup.startsystemservcie(context);//Start Upload to FTP Server Service
    }
}

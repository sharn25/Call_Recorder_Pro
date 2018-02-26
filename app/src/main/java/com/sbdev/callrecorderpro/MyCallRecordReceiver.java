package com.sbdev.callrecorderpro;

import android.content.Context;

import com.sbdev.callrecorderprohelper.CallRecord;
import com.sbdev.callrecorderprohelper.receiver.CallRecordReceiver;

import java.util.Date;



public class MyCallRecordReceiver extends CallRecordReceiver {

    public MyCallRecordReceiver(CallRecord callRecord) {
        super(callRecord);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        //callRecord.disableSaveFile();
        super.onOutgoingCallStarted(ctx, number, start);
    }
}

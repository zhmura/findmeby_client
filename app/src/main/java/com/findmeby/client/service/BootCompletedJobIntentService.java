package com.findmeby.client.service;

import android.content.Context;
import android.content.Intent;

import com.findmeby.client.SplashActivity_;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class BootCompletedJobIntentService extends JobIntentService {

    public static final int JOB_ID = 0x01;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, BootCompletedJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        SplashActivity_.intent(getApplicationContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }

}

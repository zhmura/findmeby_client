package com.findmeby.client.service;

import android.app.IntentService;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;

import com.findmeby.client.R;
import com.findmeby.client.constant.PrefConstants;
import com.findmeby.client.receiver.SyncAlarmManager;


import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import timber.log.Timber;

public class SyncService extends IntentService {

    private static final String TAG = "SyncService";

    public static final String ACTION_SYNC = "com.findmeby.client.service.action.GET_NEW_MESSAGES";
    private static final String NOTIFICATION_CHANNEL_ID = "sync.ch1";


    public SyncService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startCommand();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent == null) {
            return START_NOT_STICKY;
        }
        startCommand();
        return START_STICKY;
    }


    private void startCommand() {
        startInForeground();
    }

    private void startInForeground() {
        Intent notificationIntent = new Intent(this, SyncService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_appintro_fab_skip)
                .setContentTitle("findmeby")
                .setContentText("sync")
                .setTicker("findmeby")
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "messagecheck.channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Truckdoc message channel");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        try {
            startForeground((int) (System.currentTimeMillis() % 10000), notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        Timber.i("Destroying service");
        this.stopSelf();
        super.onDestroy();
    }


    private static void enableSync(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean(PrefConstants.ENABLE_SYNC, true).apply();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            //Checker.checkDataEnabled(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean syncEnabled = sharedPreferences.getBoolean(PrefConstants.ENABLE_SYNC, false);
            String action = intent.getAction();
            if (syncEnabled && ACTION_SYNC.equals(action)) {
                processGetNewMessagesActionIfNecessary(intent);
            }
        } catch (Exception e) {
            Timber.e(e, "Handle intent issue with action:" + intent.getAction());
        }
    }


    private void processGetNewMessagesActionIfNecessary(Intent intent) {
        SyncAlarmManager.setSyncAlarm(getApplicationContext(), false);
        getNewMessagesAction();
    }
    /**
     */
    private void getNewMessagesAction() {
    }



    private void unlockDevice() {
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock("TAG");

        PowerManager pm2 = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        final PowerManager.WakeLock wakeLock = pm2.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "MyWakeLock:");
        wakeLock.acquire();

        wakeLock.release();
    }

    public static void executeGetNewMessagesAction(Context context) {
        Intent intent = new Intent(SyncService.ACTION_SYNC, null, context, SyncService.class);
        ContextCompat.startForegroundService(
                context,
                intent
        );
    }
}

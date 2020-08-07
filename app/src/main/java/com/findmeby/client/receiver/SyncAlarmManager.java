package com.findmeby.client.receiver;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;


import com.findmeby.client.service.SyncService;

import androidx.annotation.NonNull;


/**
 * @author Siarhei Zhmura
 */
public class SyncAlarmManager extends BroadcastReceiver {

    public static String ACTION = "com.findmeby.client.receiver.SyncAlarm";

    public static final int AUTOSYNC_NOTIFICATION_ID = 2;
    public static final int INTENT_GET_NEW_MESSAGE_ALARM = 43;
    public static final String GET_NEW_MESSAGES_ALARM_INTENT_EXTRA_KEY = "get_new_messages";

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (intent.getBooleanExtra(GET_NEW_MESSAGES_ALARM_INTENT_EXTRA_KEY, false)) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TRUCKDOC_TAG:");
            wl.acquire(); // TODO: Investigate if this lock makes any sense because service execution is asynchronous.
            SyncService.executeGetNewMessagesAction(context);
            wl.release();
        }
    }

    /**
     * Resets alarm according to current settings.
     *
     * @param triggerSyncAttempt trigger immediate update
     */
    public static void setSyncAlarm(Context context, boolean triggerSyncAttempt) {
        setMessageAlarmStartingAt(context, 600, System.currentTimeMillis() + 600);
    }

    private static void setMessageAlarmStartingAt(Context context, long syncIntervalMs,
                                                  long nextAlarmActivation) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SyncAlarmManager.class);
        intent.setAction(ACTION);
        intent.putExtra(GET_NEW_MESSAGES_ALARM_INTENT_EXTRA_KEY, true);
        PendingIntent pi = PendingIntent.getBroadcast(context, INTENT_GET_NEW_MESSAGE_ALARM, intent, 0);
        assert am != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextAlarmActivation, pi);
        } else {
            am.setRepeating(AlarmManager.RTC_WAKEUP, nextAlarmActivation, syncIntervalMs, pi);
        }
    }

    public static void cancelSyncAlarm(Context context) {
        NotificationManager notifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyMgr.cancel(AUTOSYNC_NOTIFICATION_ID);
        Intent intent = new Intent(context, SyncAlarmManager.class);
        intent.putExtra(GET_NEW_MESSAGES_ALARM_INTENT_EXTRA_KEY, true);
        PendingIntent sender = PendingIntent.getBroadcast(context, INTENT_GET_NEW_MESSAGE_ALARM, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        sender.cancel();
        alarmManager.cancel(sender);
    }

}

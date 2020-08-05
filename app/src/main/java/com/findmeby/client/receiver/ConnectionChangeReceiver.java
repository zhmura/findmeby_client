package com.findmeby.client.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class ConnectionChangeReceiver extends BroadcastReceiver {

    public static final long MIN_NETWORK_CHANGE_NOTIFICATION_INTERVAL = TimeUnit.MINUTES.toMillis(15);

    @Override
    public void onReceive(Context context, Intent intent) {
        if (haveNetworkConnection(context)) {
            Toast.makeText(context, "Восстановилось соединение", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networks = cm.getAllNetworks();
        for (Network network: networks) {
            NetworkInfo netInfo = cm.getNetworkInfo(network);
                if (netInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                    if (netInfo.isConnected()) {
                        haveConnectedWifi = true;
                    }
                }
                if (netInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                    if (netInfo.isConnected()) {
                        haveConnectedMobile = true;
                    }
                }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
package com.findmeby.client;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.findmeby.client.constant.PrefConstants;
import com.findmeby.client.intro.PermissionsIntro;
import com.findmeby.client.model.Contact;
import com.findmeby.client.network.BackendService;
import com.findmeby.client.network.model.Alarm;
import com.findmeby.client.network.model.Contacts;
import com.findmeby.client.network.model.Location;
import com.findmeby.client.network.model.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    @ViewById(R.id.cancel)
    Button cancel;
    @ViewById(R.id.alarm)
    Button alarm;
    @ViewById(R.id.settings)
    Button settings;

    SharedPreferences sharedPreferences;
    FusedLocationProviderClient client;

    @AfterViews
    public void init() {
        client = LocationServices.getFusedLocationProviderClient(this);
    }

    @Click(R.id.settings)
    void onSettings() {
        SettingsActivity_.intent(HomeActivity.this).start();
    }

    @Click(R.id.alarm)
    void onAlarm() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            ActivityCompat.requestPermissions(this, info.requestedPermissions, 1);
            return;
        }
        final android.location.Location[] location = new android.location.Location[1];
        client.getLastLocation().addOnSuccessListener(HomeActivity.this, new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                location[0] = (android.location.Location) o;
                sendAlarm(location[0]);
            }
        });
    }

    private void sendAlarm(android.location.Location location) {
        Alarm alarm = new Alarm().withCurrentTimestamp(System.currentTimeMillis())
                .withAccountToken(sharedPreferences.getString(PrefConstants.ACCOUNT_TOKEN,
                        "Unknown"))
                .withLocation(new Location(BigDecimal.valueOf(location.getLatitude()).floatValue(),
                                            BigDecimal.valueOf(location.getLongitude()).floatValue(),
                                            System.currentTimeMillis()))
                .withAccountToken(UUID.randomUUID().toString())
                .withCurrentTimestamp(System.currentTimeMillis())
                .withOriginalTimestamp(System.currentTimeMillis());
        try {
            Response r = BackendService.getInstance().triggerAlarm(alarm).get();
            r.getSuccess();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getValue(String prefKey) {
        return sharedPreferences.getString(prefKey, "");
    }

    private void setValue(String prefKey, Boolean value) {
        sharedPreferences.edit().putBoolean(prefKey, value).apply();
    }

}

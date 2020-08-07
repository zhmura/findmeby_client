package com.findmeby.client.intro;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.findmeby.client.ContactsIntroFragment;
import com.findmeby.client.UsernameIntroFragment;
import com.findmeby.client.constant.PrefConstants;
import com.findmeby.client.network.BackendService;
import com.findmeby.client.network.model.Contacts;
import com.findmeby.client.network.model.Response;
import com.github.appintro.AppIntro2;
import com.github.appintro.AppIntroCustomLayoutFragment;
import com.github.appintro.AppIntroPageTransformerType;
import com.findmeby.client.HomeActivity_;
import com.findmeby.client.R;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static rx.internal.operators.NotificationLite.getValue;


public class PermissionsIntro extends AppIntro2 {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_welcome));
        addSlide(new UsernameIntroFragment());
        addSlide(new ContactsIntroFragment());
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_permissions));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_instructions));

        // Set intro custom background
        setBackgroundResource(R.drawable.back_slide);

        // Change the color of the dot indicator.
        setIndicatorColor(Color.RED, Color.BLACK);

        // Request mandatory camera and location permission
        askForPermissions(
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                4, true);
        setSkipButtonEnabled(false);
    }

    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        saveContacts(sharedPreferences);
        sharedPreferences.edit().putInt(PrefConstants.INTRO_DONE, 1).apply();
        sharedPreferences.edit().putString(PrefConstants.ACCOUNT_TOKEN, UUID.randomUUID().toString()).apply();

        HomeActivity_.intent(this).start();
        finish();
    }

    private void saveContacts(SharedPreferences sharedPreferences) {
        Contacts contacts = new Contacts().withContacts(Arrays.asList(
                getValue(PrefConstants.CONTACT_1),
                getValue(PrefConstants.CONTACT_2),
                getValue(PrefConstants.CONTACT_3))).withLetContactsSeeGeoHistory(true)
                .withAccountToken(UUID.randomUUID().toString())
                .withCurrentTimestamp(System.currentTimeMillis())
                .withMessageText("Alarm client test")
                .withSendPeriodicGeoData(true)
                .withUserName(getValue(PrefConstants.USER_NAME));

        BackendService.getInstance().registerContacts(contacts).thenApply(response -> {
            sharedPreferences.edit().putBoolean(PrefConstants.CONTACTS_REGISTERED, response.getSuccess()).apply();
            Toast.makeText(this, "Contacts saved on server", Toast.LENGTH_LONG).show();
            return response;
        }).exceptionally(t -> {
            Toast.makeText(this, "Contacts are not saved on server: " + t.getMessage(), Toast.LENGTH_LONG).show();
            return new Response();
        });
    }
}
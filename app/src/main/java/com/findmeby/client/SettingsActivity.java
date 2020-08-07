package com.findmeby.client;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.widget.Button;
import android.widget.EditText;

import com.findmeby.client.constant.PrefConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends AppCompatActivity {

    @ViewById(R.id.yourName)
    EditText userName;
    @ViewById(R.id.contact1)
    EditText contact1;
    @ViewById(R.id.contact2)
    EditText contact2;
    @ViewById(R.id.contact3)
    EditText contact3;

    SharedPreferences sharedPreferences;

    @AfterViews
    public void init() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName.setText(getValue(PrefConstants.USER_NAME));
        contact1.setText(getValue(PrefConstants.CONTACT_1));
        contact2.setText(getValue(PrefConstants.CONTACT_2));
        contact3.setText(getValue(PrefConstants.CONTACT_3));
        userName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isBlank(userName.getText())){
                    userName.setError("User name can not be empty");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.USER_NAME, String.valueOf(s)).apply();
                }
            }
        });

        contact1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmailValidator.getInstance().isValid(String.valueOf(contact1.getText()))){
                    contact1.setError("You should enter a valid email");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_1, String.valueOf(s)).apply();
                }
            }
        });

        contact2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmailValidator.getInstance().isValid(String.valueOf(contact2.getText()))){
                    contact1.setError("You should enter a valid email");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_2, String.valueOf(s)).apply();
                }
            }
        });

        contact3.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmailValidator.getInstance().isValid(String.valueOf(contact3.getText()))){
                    contact1.setError("You should enter a valid email");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_3, String.valueOf(s)).apply();
                }
            }
        });
    }

    private String getValue(String prefKey) {
        return sharedPreferences.getString(prefKey, "");
    }
}

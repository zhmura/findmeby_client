package com.findmeby.client;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.findmeby.client.constant.PrefConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;

@EFragment(R.layout.intro_trusted_emails)
public class ContactsIntroFragment extends Fragment {

    EditText contact1;
    EditText contact2;
    EditText contact3;

    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.intro_trusted_emails, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        contact1 = (EditText) V.findViewById(R.id.contact1intro);
        contact2 = (EditText) V.findViewById(R.id.contact2intro);
        contact3 = (EditText) V.findViewById(R.id.contact3intro);

        contact1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!EmailValidator.getInstance().isValid(String.valueOf(contact1.getText()))) {
                    contact1.setError("Email is not valid");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_1,
                            String.valueOf(s)).apply();
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
                if (!EmailValidator.getInstance().isValid(String.valueOf(contact2.getText()))) {
                    contact2.setError("Email is not valid");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_2,
                            String.valueOf(s)).apply();
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
                if (!EmailValidator.getInstance().isValid(String.valueOf(contact3.getText()))) {
                    contact3.setError("Email is not valid");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.CONTACT_3,
                            String.valueOf(s)).apply();
                }
            }
        });

        return V;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        sharedPreferences.edit().putString(PrefConstants.CONTACT_1,
                String.valueOf(contact1.getText())).apply();
        sharedPreferences.edit().putString(PrefConstants.CONTACT_2,
                String.valueOf(contact2.getText())).apply();
        sharedPreferences.edit().putString(PrefConstants.CONTACT_3,
                String.valueOf(contact3.getText())).apply();
    }

    private String getValue(String prefKey) {
        return sharedPreferences.getString(prefKey, "");
    }
}
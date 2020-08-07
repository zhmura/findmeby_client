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

import org.androidannotations.annotations.EFragment;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;

@EFragment(R.layout.intro_your_creds)
public class UsernameIntroFragment extends Fragment {

    EditText userName;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.intro_your_creds, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        userName = (EditText) V.findViewById(R.id.usernameIntro);
        userName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.isBlank(userName.getText())) {
                    userName.setError("User name can not be empty");
                } else {
                    sharedPreferences.edit().putString(PrefConstants.USER_NAME,
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
        sharedPreferences.edit().putString(PrefConstants.USER_NAME,
                String.valueOf(userName.getText())).apply();
    }

    private String getValue(String prefKey) {
        return sharedPreferences.getString(prefKey, "");
    }


}

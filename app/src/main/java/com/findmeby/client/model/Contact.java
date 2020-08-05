package com.findmeby.client.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wim on 4/26/16.
 */
public class Contact implements Parcelable {

    private int id;
    private String email;

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.email);
    }

}
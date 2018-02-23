package com.example.lenovo.nontonapps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo on 23/02/2018.
 */

public class User implements Parcelable {

    private String nama;
    private String username;
    private String password;
    private String email;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
    }

    protected User(Parcel in) {
        this.nama = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

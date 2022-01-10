package com.ryucant.testcodingandroid_indodana.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Follow {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(avatarUrl);
        parcel.writeString(login);
        parcel.writeString(htmlUrl);
    }

    protected Follow(Parcel in) {
        avatarUrl = in.readString();
        login = in.readString();
        htmlUrl = in.readString();
    }

    public static final Parcelable.Creator<Follow> CREATOR = new Parcelable.Creator<Follow>() {
        @Override
        public Follow createFromParcel(Parcel in) {
            return new Follow(in);
        }

        @Override
        public Follow[] newArray(int size) {
            return new Follow[size];
        }
    };

}

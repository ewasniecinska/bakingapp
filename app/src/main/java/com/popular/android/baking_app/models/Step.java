package com.popular.android.baking_app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    private int id;

    private String shortDescription;

    private String description;

    private String videoURL;

    private String thumbnailURL;

    public Step() {
    }

    protected Step(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
    }

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public Step setId(int id) {
        this.id = id;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Step setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Step setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public Step setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public Step setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);
    }
}
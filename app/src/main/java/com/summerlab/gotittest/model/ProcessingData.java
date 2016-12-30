package com.summerlab.gotittest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trieulh on 12/30/16.
 */
public class ProcessingData{
    @SerializedName("uid")
    private int uid;
    @SerializedName("orig_name")
    private String orig_name;
    @SerializedName("avatar")
    private String avatar;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOrig_name() {
        return orig_name;
    }

    public void setOrig_name(String orig_name) {
        this.orig_name = orig_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

/*
"uid": "837069",
      "name": "Z. C.",
      "orig_name": "Ananth Jayan",
      "avatar": "https://s3.amazonaws.com/img1.tutorpl.us/a/u0838k/837069_45.jpg"
 */
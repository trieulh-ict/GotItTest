package com.summerlab.gotittest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trieulh on 12/30/16.
 */
public class Author  extends RealmObject{
    @PrimaryKey
    private int uid;

    private String name,avatar;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

/*
"uid": "607205",
      "name": "N4dia ",
      "avatar": "https://secure.gravatar.com/avatar/df1a310fcb4516f6420bc43d241c18ec?s=45&d=identicon"
 */

package com.summerlab.gotittest.model;

import io.realm.RealmObject;

/**
 * Created by trieulh on 12/30/16.
 */
public class Attachment extends RealmObject{
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

/*

 */

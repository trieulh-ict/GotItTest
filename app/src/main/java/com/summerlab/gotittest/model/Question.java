package com.summerlab.gotittest.model;

import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trieulh on 12/30/16.
 */
public class Question extends RealmObject {
    @PrimaryKey
    private int qid;

    private String title;
    private int tid,processing_status;
    private ProcessingData processing_data;
    private String created,updated;
    private Author author;

    @Ignore
    public List<Attachment> attachments;


    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getProcessing_status() {
        return processing_status;
    }

    public void setProcessing_status(int processing_status) {
        this.processing_status = processing_status;
    }

    public ProcessingData getProcessing_data() {
        return processing_data;
    }

    public void setProcessing_data(ProcessingData processing_data) {
        this.processing_data = processing_data;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}

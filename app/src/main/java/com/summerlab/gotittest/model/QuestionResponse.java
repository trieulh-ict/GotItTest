package com.summerlab.gotittest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trieulh on 12/30/16.
 */
public class QuestionResponse{
    @SerializedName("qid")
    private int qid;

    @SerializedName("title")
    private String title;
    @SerializedName("tid")
    private int tid;
    @SerializedName("processing_status")
    private int processing_status;
    @SerializedName("processing_data")
    private ProcessingData processing_data;
    @SerializedName("created")
    private long created;
    @SerializedName("updated")
    private long updated;
    @SerializedName("author")
    private Author author;
    @SerializedName("attachments")
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

    public String getProcessing_status() {
        switch (processing_status){
            case 5:
                return "Answered";
            case 6:
                return "Dead";
            default:
                return "Wrong Status";
        }
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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}

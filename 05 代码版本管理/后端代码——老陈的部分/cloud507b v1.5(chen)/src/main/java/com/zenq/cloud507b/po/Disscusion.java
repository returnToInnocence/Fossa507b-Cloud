package com.zenq.cloud507b.po;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenxi
 * @since 2023-07-01
 */
public class Disscusion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String tweetId;

    private String observer;

    private String comment;

    public Disscusion(String id, String tweetId, String observer, String comment) {
        this.id = id;
        this.tweetId = tweetId;
        this.observer = observer;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }
    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Disscusion{" +
            "id=" + id +
            ", tweetId=" + tweetId +
            ", observer=" + observer +
            ", comment=" + comment +
        "}";
    }
}

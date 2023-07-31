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

    private String time;

    public Disscusion(String id, String tweetId, String observer, String comment, String time) {
        this.id = id;
        this.tweetId = tweetId;
        this.observer = observer;
        this.comment = comment;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

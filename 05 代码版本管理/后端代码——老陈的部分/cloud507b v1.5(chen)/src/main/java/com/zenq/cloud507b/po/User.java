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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private String usermail;

    private String userpsd;

    private String usericon;

    private String userstatus;

    private String usergroup;
    public User() {
        super();
    }
    public User(String uid, String name) {
        super();
        this.userid = uid;
        this.username = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }
    public String getUserpsd() {
        return userpsd;
    }

    public void setUserpsd(String userpsd) {
        this.userpsd = userpsd;
    }
    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }
    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }
    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    @Override
    public String toString() {
        return "User{" +
            "userid=" + userid +
            ", username=" + username +
            ", usermail=" + usermail +
            ", userpsd=" + userpsd +
            ", usericon=" + usericon +
            ", userstatus=" + userstatus +
            ", usergroup=" + usergroup +
        "}";
    }
}

package com.pls.accesstoken.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 81046 on 2018-07-12
 */
@Entity
@Table(name = "TB_ACCESS_TOKENS")
public class AccessTokens {

    @Id
    @Column(name = "APPID")
    private String appid;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "SAVE_TIME")
    private String saveTime;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokens() {
    }

    @Override
    public String toString() {
        return "AccessTokens{" +
                "appid='" + appid + '\'' +
                ", saveTime='" + saveTime + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
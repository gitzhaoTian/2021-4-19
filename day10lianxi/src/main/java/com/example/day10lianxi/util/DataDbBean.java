package com.example.day10lianxi.util;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String url;
    private String title;
    private String desc;
    @Generated(hash = 1323400805)
    public DataDbBean(Long id, String url, String title, String desc) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.desc = desc;
    }
    @Generated(hash = 1222557525)
    public DataDbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}

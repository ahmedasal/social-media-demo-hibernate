package com.social.media.model;


import java.sql.Timestamp;
import java.util.Date;

public class Page {
    int id;
    String pageName;
    Timestamp createPageDate;
    int adminUser;

    public Page(int id, String pageName) {
        this.id = id;
        this.pageName = pageName;
    }

    public Page() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Timestamp getCreatePageDate() {
        createPageDate = new Timestamp(new java.util.Date().getTime());
        return createPageDate;
    }

    public void setCreatePageDate(Timestamp createPageDate) {
        this.createPageDate = createPageDate;
    }

    public int getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(int adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", createPageDate=" + createPageDate +
                '}';
    }
}

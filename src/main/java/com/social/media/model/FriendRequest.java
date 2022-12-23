package com.social.media.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "friendship_request")
public class FriendRequest {
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "user1")
    User senderUser;
    @ManyToOne
    @JoinColumn(name = "user2")
    User ReceiverUser;
    @Column(name = "create_date")
    Date CreateDate;
    String comfirmStatus;
    @Column(name = "confirm_date")
    Date comfirmDate;

    public FriendRequest() {
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return ReceiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        ReceiverUser = receiverUser;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getComfirmStatus() {
        return comfirmStatus;
    }

    public void setComfirmStatus(String comfirmStatus) {
        this.comfirmStatus = comfirmStatus;
    }

    public Date getComfirmDate() {
        return comfirmDate;
    }

    public void setComfirmDate(Date comfirmDate) {
        this.comfirmDate = comfirmDate;
    }
}

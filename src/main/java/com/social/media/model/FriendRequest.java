package com.social.media.model;

public class FriendRequest {
    int id;
    int senderUser;
    int ReceiverUser;
    String CreateDate;
    String comfirmStatus;
    String comfirmDate;

    public FriendRequest() {
        }

    public FriendRequest(int senderUser, int receiverUser, String createDate, String comfirmStatus, String comfirmDate) {
        this.senderUser = senderUser;
        ReceiverUser = receiverUser;
        CreateDate = createDate;
        this.comfirmStatus = comfirmStatus;
        this.comfirmDate = comfirmDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(int senderUser) {
        this.senderUser = senderUser;
    }

    public int getReceiverUser() {
        return ReceiverUser;
    }

    public void setReceiverUser(int receiverUser) {
        ReceiverUser = receiverUser;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getComfirmStatus() {
        return comfirmStatus;
    }

    public void setComfirmStatus(String comfirmStatus) {
        this.comfirmStatus = comfirmStatus;
    }

    public String getComfirmDate() {
        return comfirmDate;
    }

    public void setComfirmDate(String comfirmDate) {
        this.comfirmDate = comfirmDate;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", senderUser=" + senderUser +
                ", ReceiverUser=" + ReceiverUser +
                ", CreateDate='" + CreateDate + '\'' +
                ", comfirmStatus='" + comfirmStatus + '\'' +
                ", comfirmDate='" + comfirmDate + '\'' +
                '}';
    }
}

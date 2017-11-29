package com.example.anhkhoa.testbuttonlike;

/**
 * Created by anh khoa on 11/18/2017.
 */

public class Comment {
    public String idcomment;
    public String iduser;
    public String cmtContent;
    public int count;
    public int idlike;

    public Comment() {
    }

    public Comment(String idcomment, String iduser, String cmtContent, int count, int idlike) {
        this.idcomment = idcomment;
        this.iduser = iduser;
        this.cmtContent = cmtContent;
        this.count = count;
        this.idlike = idlike;
    }

    public String getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(String idcomment) {
        this.idcomment = idcomment;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIdlike() {
        return idlike;
    }

    public void setIdlike(int idlike) {
        this.idlike = idlike;
    }
}

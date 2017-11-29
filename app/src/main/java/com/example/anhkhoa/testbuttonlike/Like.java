package com.example.anhkhoa.testbuttonlike;

/**
 * Created by anh khoa on 11/18/2017.
 */

public class Like {
    public String likettype;
    public boolean likebit;

    public Like() {
    }

    public Like(String likettype, boolean likebit) {
        this.likettype = likettype;
        this.likebit = likebit;
    }

    public String getLikettype() {
        return likettype;
    }

    public void setLikettype(String likettype) {
        this.likettype = likettype;
    }

    public boolean isLikebit() {
        return likebit;
    }

    public void setLikebit(boolean likebit) {
        this.likebit = likebit;
    }
}

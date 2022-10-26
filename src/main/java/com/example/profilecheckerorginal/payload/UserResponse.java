package com.example.profilecheckerorginal.payload;

public class UserResponse {
    private String nickName;
    private String time;
    private String date;

    public UserResponse() {
    }

    public UserResponse(String nickName, String time, String date) {
        this.nickName = nickName;
        this.time = time;
        this.date = date;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

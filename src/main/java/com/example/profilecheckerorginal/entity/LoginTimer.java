package com.example.profilecheckerorginal.entity;

import javax.persistence.*;

@Entity
public class LoginTimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;
    @Column(name = "nick")
    private String nick;
    public LoginTimer() {
    }
    public LoginTimer(String time, String date, String nick) {
        this.time = time;
        this.date = date;
        this.nick = nick;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}

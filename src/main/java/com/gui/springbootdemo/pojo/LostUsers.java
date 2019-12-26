package com.gui.springbootdemo.pojo;

import javax.persistence.*;

/**
 * @ClassName User
 * @Description TODO
 * @Author MI
 * @Date 2019/12/25 23:01
 * @Version 1.0
 */
@Entity
@Table(name = "lost_users")
public class LostUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 255)
    private int id;

    @Column(length = 20)
    private String userName;

    @Column(length = 20)
    private String passWord;

    @Column(length = 11)
    private String phone;

    @Column(length = 1)
    private int level = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LostUsers{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }
}

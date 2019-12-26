package com.gui.springbootdemo.pojo;

import javax.persistence.*;

/**
 * @ClassName LostRecord
 * @Description TODO
 * @Author MI
 * @Date 2019/12/25 23:16
 * @Version 1.0
 */
@Entity
@Table(name = "lost_record")
public class LostRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(length = 20)
    private int lostUserId;

    @Column(length = 20)
    private String title;

    @Column
    private String content;

    //若为1表示是失物招领，若为2表示寻物启事
    @Column(length = 1)
    private int typeCode;

    //若为0表示展示，若为1表示记录已完成，若为2记录过期
    @Column(length = 1)
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLostUserId() {
        return lostUserId;
    }

    public void setLostUserId(int lostUserId) {
        this.lostUserId = lostUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LostRecord{" +
                "id=" + id +
                ", lostUserId='" + lostUserId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", typeCode=" + typeCode +
                ", status=" + status +
                '}';
    }
}

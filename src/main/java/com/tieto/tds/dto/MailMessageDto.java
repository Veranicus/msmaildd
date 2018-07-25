package com.tieto.tds.dto;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name = "mail")
public class MailMessageDto {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "user_token")
    private String usertoken;
    @Column(name = "send_to")
    private String sendTo;
    @Column(name = "subject")
    private String subject;
    @Column(name = "content")
    private String content;

    private File file;


    public MailMessageDto() {
    }

    public MailMessageDto(String usertoken, String sendTo, String subject, String content) {
        this.usertoken = usertoken;
        this.sendTo = sendTo;
        this.subject = subject;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    @Override
    public String toString() {
        return "MailMessageDto{" +
                "id=" + id +
                ", usertoken='" + usertoken + '\'' +
                ", sendTo='" + sendTo + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", file=" + file +
                '}';
    }
}

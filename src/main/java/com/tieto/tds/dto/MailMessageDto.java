package com.tieto.tds.dto;

import java.io.File;

public class MailMessageDto {

	private String usertoken;
	private String sendTo;
	private String subject;
	private String content;
	private File file;

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
}

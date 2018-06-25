package com.tieto.tds.service;

import com.tieto.tds.dto.MailMessageDto;

public interface MailService {

	public void prepareAndSend(MailMessageDto messageDto);
}

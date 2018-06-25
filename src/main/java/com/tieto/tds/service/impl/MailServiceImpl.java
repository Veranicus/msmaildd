package com.tieto.tds.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.tieto.tds.dto.MailMessageDto;
import com.tieto.tds.service.MailContentBuilder;
import com.tieto.tds.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Value("mail.from-email")
	private String sendFrom;

	private JavaMailSender mailSender;
	private MailContentBuilder mailContentBuilder;

	@Autowired
	public MailServiceImpl(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	@Override
	public void prepareAndSend(final MailMessageDto messageDto) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setTo(messageDto.getSendTo());
			messageHelper.setSubject(messageDto.getSubject());
			messageHelper.setFrom(sendFrom);
			messageHelper.setReplyTo(sendFrom);
			if (messageDto.getFile() != null){
				log.debug("File atachment = {}", messageDto.getFile().getName());
				messageHelper.addAttachment(messageDto.getFile().getName(), messageDto.getFile());
			}
			String content = mailContentBuilder.build(messageDto.getContent());
			log.debug(content);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
		}
	}
}

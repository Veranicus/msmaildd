package com.tieto.tds.controler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tieto.tds.dto.MailMessageDto;
import com.tieto.tds.service.MailService;

@RestController
public class MainConroller {

	private static final Logger log = LoggerFactory.getLogger(MainConroller.class);

	private MailService mailService;

	@Autowired
	public MainConroller(MailService mailService) {
		this.mailService = mailService;
	}

	@PostMapping("/send")
	public void send(
			@RequestParam("usertoken") String usertoken,
			@RequestParam("sendTo") String sendTo,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(required = false, name="file") MultipartFile multipartFile,
			@RequestParam(required = false, name="files") List<MultipartFile> multipartFiles) {
		log.debug("{}, {}, {}, {}", usertoken, sendTo, subject, content);
		MailMessageDto messageDto = new MailMessageDto();
		messageDto.setUsertoken(usertoken);
		messageDto.setSendTo(sendTo);
		messageDto.setSubject(subject);
		messageDto.setContent(content);
		if (!multipartFile.isEmpty()){
			System.out.println("File is present " + multipartFile.getOriginalFilename());
			File convFile = new File( multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(convFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			messageDto.setFile(convFile);

			multipartFiles.forEach( file ->{
				System.out.println(file.getOriginalFilename());
			});
			mailService.prepareAndSend(messageDto);
		}
	}
}

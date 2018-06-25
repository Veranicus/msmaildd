package com.tieto.tds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.tieto.tds.dto.MailMessageDto;
import com.tieto.tds.service.impl.MailServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MsmailApplication.class)
public class MailClientTest {

	private GreenMail smtpServer;

	@Before
	public void setUp() throws Exception {
		smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
		smtpServer.start();
	}

	@After
	public void tearDown() throws Exception {
		smtpServer.stop();
	}

	@Autowired
	private MailServiceImpl mailClient;

	@Test
	public void shouldSendMail() throws Exception {
		//given
		MailMessageDto message = new MailMessageDto();
		message.setSendTo("john.doe@nomail.com");
		message.setContent("Test message content");
		message.setSubject("Mail test");
		//when
		
		mailClient.prepareAndSend(message);
		//then
		String content = "<span>" + message + "</span>";
	    assertReceivedMessageContains(content);
	}

	private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
		MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		String content = (String) receivedMessages[0].getContent();
		assertTrue(content.contains(expected));
	}
}
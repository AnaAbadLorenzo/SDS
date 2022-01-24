package com.sds.service.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sds.service.mail.MailService;
import com.sds.service.mail.model.Mail;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void enviarCorreo(final Mail mail) throws MessagingException {
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setTo(mail.getReceptorEmail());
		mimeMessageHelper.setFrom(mail.getEmisorEmail());
		mimeMessageHelper.setSubject(mail.getAsuntoEmail());
		mimeMessageHelper.setText(mail.getContenidoEmail());

		javaMailSender.send(mimeMessageHelper.getMimeMessage());

	}

}

package com.sds.service.mail;

import javax.mail.MessagingException;

import com.sds.service.mail.model.Mail;

public interface MailService {

	public void enviarCorreo(Mail mail) throws MessagingException;
}

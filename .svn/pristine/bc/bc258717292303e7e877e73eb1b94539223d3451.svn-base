package com.webside.util;

import javax.mail.AuthenticationFailedException;

import jodd.mail.Email;
import jodd.mail.MailException;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: EmailUtil
 * @Description: 邮件发送工具类,封装了jodd的mail工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:22:12
 *
 */
@Component
public class EmailUtil {
	

	@Value("${mail.username}")
	private String mailUser;

	@Value("${mail.password}")
	private String mailPassword;
	
	@Value("${mail.smtp}")
	private String mailSmtp;


	/**
	 * 发送邮箱
	 * 
	 * @param toMail
	 * @param subject
	 * @param text
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean sendMail(String toMail, String subject, String text) throws AuthenticationFailedException,MailException{
		Email email = Email.create().from(mailUser).to(toMail).cc(mailUser)
				.subject(subject).addText(text);
		SmtpServer smtpServer = SmtpServer.create(mailSmtp)
				.authenticateWith(mailUser, mailPassword);
		SendMailSession session = smtpServer.createSession();
		session.open();
		String result = session.sendMail(email);
		session.close();
		if (result ==null) {
			return false;
		}
		return true;
	}
	
	
	public static void main(String args[]) {
		
		EmailUtil mail = new EmailUtil();
		
		mail.mailUser = "service400123@163.com";
		mail.mailPassword = "code400123";
		mail.mailSmtp = "smtp.163.com";
		
		try {
			mail.sendMail("2297492527@qq.com", "会员注册-勿回复", "欢迎加入超级无敌飞行俱乐部");
		} catch (MailException | AuthenticationFailedException e) {
			e.printStackTrace();
		}
	}
}

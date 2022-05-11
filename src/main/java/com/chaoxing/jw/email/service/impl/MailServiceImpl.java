package com.chaoxing.jw.email.service.impl;

import com.chaoxing.jw.email.service.MailService;
import com.chaoxing.jw.email.upload.DownloadFileHttpCilent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

/**
 * @version 1.0
 * @Auther: hemingyang
 * @Date: 2021/11/15 17:37
 * @Description:
 */
@Component
public class MailServiceImpl implements MailService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    //发件人
    @Value("${spring.mail.username}")
    private String from;


    /**
     * Description: 文本邮件
     *
     * @auther: hemingyang
     * @param: [to, subject, content]
     * @return: void
     * @date: 2021/11/16 11:48
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }

    }

    /**
     * Description: html邮件
     *
     * @auther: hemingyang
     * @param: [to, subject, content]
     * @return: void
     * @date: 2021/11/16 11:48
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (javax.mail.MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * Description: 发送带附件的邮件
     *
     * @auther: hemingyang
     * @param: []
     * @return: void
     * @date: 2021/11/16 11:49
     */
    @Override
    public void sendAttachmentsMail() {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("21708454@qq.com");
            helper.setCc("341122075@qq.com");
            helper.setSubject("请复制url在新的新的网页打开");
            DownloadFileHttpCilent downloadFileHttpCilent = new DownloadFileHttpCilent();
            String url = downloadFileHttpCilent.download();
            helper.setText(url, true);
            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }

}
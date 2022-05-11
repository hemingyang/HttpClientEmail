package com.chaoxing.jw.email.service;

/**
 * @version 1.0
 * @Auther: hemingyang
 * @Date: 2021/11/15 17:36
 * @Description:
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);


    void sendAttachmentsMail();

}

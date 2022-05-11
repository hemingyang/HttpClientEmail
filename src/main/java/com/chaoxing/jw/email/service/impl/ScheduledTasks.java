package com.chaoxing.jw.email.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.chaoxing.jw.email.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Auther: hemingyang
 * @Date: 2021/11/15 17:51
 * @Description:
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private MailService mailService;


    /**
     * Description:
     *
     * @auther: hemingyang
     * @param: [ cron, zone , fixeDelay, ]
     * @return: void
     * @date: 2021/11/16 11:52
     */
    @Scheduled(cron = "30 2 1 * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        new Thread(() -> {
            mailService.sendAttachmentsMail();
        }).start();

    }


}
package com.example.linebot.presentation;

import com.example.linebot.presentation.replier.*;
import com.example.linebot.service.JankenResult;
import com.example.linebot.service.JankenService;
import com.example.linebot.service.Senreki;
import com.example.linebot.service.SenrekiService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;

import java.util.List;



@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }
  //  @EventMapping
//    public Message handleMessage(MessageEvent<TextMessageContent> event) {
//        Parrot parrot = new Parrot(event);
//        return parrot.reply();
//    }

    private JankenService jankenService;
    private SenrekiService senrekiService;

    public Callback(JankenService jankenService, SenrekiService senrekiService){
        this.jankenService = jankenService;
        this.senrekiService = senrekiService;
    }

    @EventMapping
    public List<Message> handleJanken(MessageEvent<ImageMessageContent> event)
        throws Exception {

            JankenResult jankenResult = jankenService.doJanken(event);


            return List.of(new ImageSizeReply(jankenResult).reply(), new JankenReply(jankenResult).reply());
        }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        String text= event.getMessage().getText();
        switch (text){
            case "戦歴":
                Senreki senreki = senrekiService.calcSenreki();
                SenrekiReply senrekiReply= new SenrekiReply(senreki);
                return senrekiReply.reply();

            default:
                Parrot parrot = new Parrot(event);
               return parrot.reply();
        }

    }



}
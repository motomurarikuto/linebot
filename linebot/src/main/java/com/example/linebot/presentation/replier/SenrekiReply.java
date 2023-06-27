package com.example.linebot.presentation.replier;

import com.example.linebot.service.SenrekiService;
import com.example.linebot.service.Senreki;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;


public class SenrekiReply implements Replier{

    private MessageEvent<TextMessageContent> event;
    public static final String MESSAGE_FORMAT = "あなたは%d戦中%d勝(勝率%fパーセント)です";
    private Senreki senreki;

    public SenrekiReply(Senreki senreki){
        this.senreki = senreki;
    }

    @Override
    public Message reply(){

        String message = String.format(MESSAGE_FORMAT, senreki.gameCount(), senreki.jibunWinCount(), senreki.jibunWinRate());
           return new TextMessage(message);

    }
}

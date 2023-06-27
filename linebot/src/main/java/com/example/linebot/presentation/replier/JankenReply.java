package com.example.linebot.presentation.replier;

import com.example.linebot.service.JankenResult;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class JankenReply implements Replier{
    public static final String MESSAGE_FORMAT = "あなた:%s,　相手:%s\n結果:%s";
    private final JankenResult jankenResult;
    private String jibun_Text;
    private String aite_Text;
    private String kekka_Text;

    public  JankenReply(JankenResult jankenResult){
        this.jankenResult = jankenResult;

    }

    @Override


    public Message reply(){
        switch (jankenResult.response().jibun()){
            case 0:
                jibun_Text = "ぐー✊";
                break;
            case 1:
                jibun_Text = "ちょき✌︎";
                break;
            case 2:
                jibun_Text = "ぱー✋";
                break;
            default:
        }
        switch (jankenResult.response().aite()){
            case 0:
                aite_Text = "ぐー✊";
                break;
            case 1:
                aite_Text = "ちょき✌︎";
                break;
            case 2:
                aite_Text = "ぱー✋";
                break;
            default:
        }
        switch (jankenResult.response().kekka()){
            case 0:
                kekka_Text = "引き分け";
                break;
            case 1:
                kekka_Text = "あんたの負けだよ";
                break;
            case 2:
                kekka_Text = "おめ、勝ちや";
                break;
            case 3:
                kekka_Text = "ちゃんとじゃんけんの画像を入れてくれよな😡";

                break;
            default:
        }
        String message =String.format(MESSAGE_FORMAT,jibun_Text,aite_Text,kekka_Text);
        return new TextMessage(message);
    }


}

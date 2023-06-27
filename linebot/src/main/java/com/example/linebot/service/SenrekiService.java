package com.example.linebot.service;
import com.example.linebot.data.JankenLog;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class SenrekiService {
    private JankenLog jankenLog;

    public SenrekiService(JankenLog jankenLog){
        this.jankenLog = jankenLog;
    }

    public Senreki calcSenreki(){
        List<JankenResponse> jankenResponses = jankenLog.selectAll();

        int  gameCount = 0;
        int  jibunWinCount = 0;
        for(JankenResponse response : jankenResponses){
            if(response.kekka() == 1 || response.kekka() == 2){
                gameCount++;
            }
            if(response.kekka() == 2){
                jibunWinCount++;
            }
        }
        float jibunWinRate = 0;
        if(gameCount != 0){
            jibunWinRate = (float)jibunWinCount *100/ (float)gameCount ;
        }
        Senreki senreki = new Senreki(gameCount,jibunWinCount,jibunWinRate);
        return senreki;
    }

}

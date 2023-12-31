package com.example.linebot.data;
import com.example.linebot.service.JankenResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.example.linebot.data.Blob;
import com.example.linebot.data.JankenAPI;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Repository
public class JankenAPI {

    @Value("${janken.api.url}")
    private String API_URL;

    private final RestTemplate restTemplate;

    public JankenAPI(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    public JankenResponse playGame(Resource imageResource) {

        HttpHeaders formHeaders = new HttpHeaders();
        formHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", imageResource);

        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(map,formHeaders);
        ResponseEntity<JankenResponse> response = restTemplate.postForEntity(API_URL, formEntity,JankenResponse.class);
        return response.getBody();

    }

}



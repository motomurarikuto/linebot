package com.example.linebot.data;
import com.example.linebot.service.JankenResponse;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JankenLog {
//Spring Frameworkのデータベース接続ライブラリを利用する
    private JdbcTemplate jdbcTemplate;

    //Spring Frameworkのデータベース接続ライブラリを初期化する
    public JankenLog(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    // janken_logデーブルにJankenResponseのデータを記録する(永続化)
    //戻り値のnは、データベースに記録できた行数
    public int insert(JankenResponse response){
        String sql = "insert into janken_log VALUES(?,?,?)";
        int n = jdbcTemplate.update(sql,response.jibun(),response.aite(),response.kekka());
        return n;
    }

    //janken_logデーブルに記録された全てのデータをJankenResponseのリストとして取得する
    public List<JankenResponse> selectAll() {
        String sql = "select jibun, aite, kekka from janken_log";
        List<JankenResponse> selected = jdbcTemplate.query(sql, new DataClassRowMapper<>(JankenResponse.class));
        return selected;
    }

}

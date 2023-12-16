package com.connect.weasport.controller.weather;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.connect.weasport.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
    @RestController : 기본으로 하위에 있는 메소드들은 모두 @ResponseBody를 가지게 된다.
    @RequestBody : 클라이언트가 요청한 XML/JSON을 자바 객체로 변환해서 전달 받을 수 있다.
    @ResponseBody : 자바 객체를 XML/JSON으로 변환해서 응답 객체의 Body에 실어 전송할 수 있다.
            클라이언트에게 JSON 객체를 받아야 할 경우는 @RequestBody, 자바 객체를 클라이언트에게 JSON으로 전달해야할 경우에는 @ResponseBody 어노테이션을 붙여주면 된다.
    @ResponseBody를 사용한 경우 View가 아닌 자바 객체를 리턴해주면 된다.
*/
@RestController
@RequestMapping("/api")
public class WeatherApiController {

    @Autowired
    UserService userService;

    @GetMapping("/weather/{id}")
    public String restApiGetIdWeather(@PathVariable int id) throws Exception {
        /*
            @ API LIST ~

            getUltraSrtNcst 초단기실황조회
            getUltraSrtFcst 초단기예보조회
            getVilageFcst 동네예보조회
            getFcstVersion 예보버전조회
        */

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();

        LocalDateTime resultDateTime;

        // 현재 시간이 00:00 ~ 05:00 사이인지 확인
        if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.of(2, 0))) {
            // 현재 날짜에서 하루를 빼서 반환
            resultDateTime = currentDateTime.minusDays(1);
        } else {
            // 현재 날짜를 반환
            resultDateTime = currentDateTime;
        }

        if (currentTime.isAfter(LocalTime.of(2,0)) && currentTime.isBefore(LocalTime.of(5,0))){
            resultDateTime = resultDateTime.withHour(2);
        }else if(currentTime.isAfter(LocalTime.of(5,0)) && currentTime.isBefore(LocalTime.of(8,0))){
            resultDateTime = resultDateTime.withHour(5);
        }else if(currentTime.isAfter(LocalTime.of(8,0)) && currentTime.isBefore(LocalTime.of(11,0))){
            resultDateTime = resultDateTime.withHour(8);
        }else if(currentTime.isAfter(LocalTime.of(11,0)) && currentTime.isBefore(LocalTime.of(14,0))){
            resultDateTime = resultDateTime.withHour(11);
        }else if(currentTime.isAfter(LocalTime.of(14,0)) && currentTime.isBefore(LocalTime.of(17,0))){
            resultDateTime = resultDateTime.withHour(14);
        }else if(currentTime.isAfter(LocalTime.of(17,0)) && currentTime.isBefore(LocalTime.of(20,0))){
            resultDateTime = resultDateTime.withHour(17);
        }else if(currentTime.isAfter(LocalTime.of(20,0)) && currentTime.isBefore(LocalTime.of(23,0))){
            resultDateTime = resultDateTime.withHour(20);
        }else{
            resultDateTime = resultDateTime.withHour(23);
        }



        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = resultDateTime.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String formattedHour = resultDateTime.format(timeFormatter);


        String address = userService.userDetail(id).getAddress();

        int nx;
        int ny;

        switch (address){
            case "서울/경기":
                nx = 62;
                ny = 125;
                break;
            case "충북":
                nx = 76;
                ny = 114;
                break;
            case "충남":
                nx = 62;
                ny = 95;
                break;
            case "전북":
                nx = 56;
                ny = 92;
                break;
            case "전남":
                nx = 74;
                ny = 65;
                break;
            case "경북":
                nx = 84;
                ny = 96;
                break;
            case "경남":
                nx = 80;
                ny = 75;
                break;
            default:
                nx = 73;
                ny = 134;

        }


        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                + "?serviceKey=p4Acpy9Xh0uvTLPmXs8ett15aNovPnOJmqwfqfkpG%2BDmQW8CWFT6gAw6MfpY49EDCLete0OECrLVNLqlFP4Qxg%3D%3D"
                + "&dataType=JSON"            // JSON, XML
                + "&numOfRows=11"             // 페이지 ROWS
                + "&pageNo=1"                 // 페이지 번호
                + "&base_date=" +  formattedDate   // 발표일자
                + "&base_time=" + formattedHour + "00"    // 발표시각
                + "&nx=" + nx                   // 예보지점 X 좌표
                + "&ny=" + ny;                  // 예보지점 Y 좌표

        HashMap<String, Object> resultMap = getDataFromJson(url, "UTF-8", "get", "");

        System.out.println("# RESULT : " + resultMap);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("result", resultMap);

        return jsonObj.toString();
    }

    @GetMapping("/weather")
    public String restApiGetWeather() throws Exception {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();

        LocalDateTime resultDateTime;

        // 현재 시간이 00:00 ~ 05:00 사이인지 확인
        if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.of(2, 0))) {
            // 현재 날짜에서 하루를 빼서 반환
            resultDateTime = currentDateTime.minusDays(1);
        } else {
            // 현재 날짜를 반환
            resultDateTime = currentDateTime;
        }

        if (currentTime.isAfter(LocalTime.of(2,0)) && currentTime.isBefore(LocalTime.of(5,0))){
            resultDateTime = resultDateTime.withHour(2);
        }else if(currentTime.isAfter(LocalTime.of(5,0)) && currentTime.isBefore(LocalTime.of(8,0))){
            resultDateTime = resultDateTime.withHour(5);
        }else if(currentTime.isAfter(LocalTime.of(8,0)) && currentTime.isBefore(LocalTime.of(11,0))){
            resultDateTime = resultDateTime.withHour(8);
        }else if(currentTime.isAfter(LocalTime.of(11,0)) && currentTime.isBefore(LocalTime.of(14,0))){
            resultDateTime = resultDateTime.withHour(11);
        }else if(currentTime.isAfter(LocalTime.of(14,0)) && currentTime.isBefore(LocalTime.of(17,0))){
            resultDateTime = resultDateTime.withHour(14);
        }else if(currentTime.isAfter(LocalTime.of(17,0)) && currentTime.isBefore(LocalTime.of(20,0))){
            resultDateTime = resultDateTime.withHour(17);
        }else if(currentTime.isAfter(LocalTime.of(20,0)) && currentTime.isBefore(LocalTime.of(23,0))){
            resultDateTime = resultDateTime.withHour(20);
        }else{
            resultDateTime = resultDateTime.withHour(23);
        }



        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = resultDateTime.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String formattedHour = resultDateTime.format(timeFormatter);



        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                + "?serviceKey=p4Acpy9Xh0uvTLPmXs8ett15aNovPnOJmqwfqfkpG%2BDmQW8CWFT6gAw6MfpY49EDCLete0OECrLVNLqlFP4Qxg%3D%3D"
                + "&dataType=JSON"            // JSON, XML
                + "&numOfRows=11"             // 페이지 ROWS
                + "&pageNo=1"                 // 페이지 번호
                + "&base_date=" +  formattedDate   // 발표일자
                + "&base_time=" + formattedHour + "00"    // 발표시각
                + "&nx=73"                    // 예보지점 X 좌표
                + "&ny=134";                  // 예보지점 Y 좌표

        HashMap<String, Object> resultMap = getDataFromJson(url, "UTF-8", "get", "");

        System.out.println("# RESULT : " + resultMap);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("result", resultMap);

        return jsonObj.toString();
    }

    public HashMap<String, Object> getDataFromJson(String url, String encoding, String type, String jsonStr) throws Exception {
        boolean isPost = false;

        if ("post".equals(type)) {
            isPost = true;
        } else {
            url = "".equals(jsonStr) ? url : url + "?request=" + jsonStr;
        }

        return getStringFromURL(url, encoding, isPost, jsonStr, "application/json");
    }

    public HashMap<String, Object> getStringFromURL(String url, String encoding, boolean isPost, String parameter, String contentType) throws Exception {
        URL apiURL = new URL(url);

        HttpURLConnection conn = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        try {
            conn = (HttpURLConnection) apiURL.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);

            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", contentType);
                conn.setRequestProperty("Accept", "*/*");
            } else {
                conn.setRequestMethod("GET");
            }

            conn.connect();

            if (isPost) {
                bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                bw.write(parameter);
                bw.flush();
                bw = null;
            }

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));

            String line = null;

            StringBuffer result = new StringBuffer();

            while ((line=br.readLine()) != null) result.append(line);

            ObjectMapper mapper = new ObjectMapper();

            resultMap = mapper.readValue(result.toString(), HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(url + " interface failed" + e.toString());
        } finally {
            if (conn != null) conn.disconnect();
            if (br != null) br.close();
            if (bw != null) bw.close();
        }

        return resultMap;
    }
}
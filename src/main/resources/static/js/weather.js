function convertTimeFormat(inputTime) {
    // 입력된 문자열을 숫자로 파싱
    let numericTime = parseInt(inputTime, 10);

    // 시간 형식으로 변환
    let hours = Math.floor(numericTime / 100);
    let minutes = numericTime % 100;

    // 12시간 형식으로 변환 (오후 여부 확인)
    let period = "오전";
    if (hours >= 12) {
        period = "오후";
        if (hours > 12) {
            hours -= 12;
        }
    }

    // 결과 문자열 반환
    return period + " " + hours + "시 " + (minutes !== 0 ? minutes + "분" : "");
}


$.ajax({
    url : "/api/weather",
    type : "get",
    contentType: "application/json",
    dataType : "json",
    success : function(data, status, xhr) {

        let dataHeader = data.result.response.header.resultCode;

        if (dataHeader == "00") {
           console.log("success == >");
           console.log(data);

            let sky = data.result.response.body.items.item[5].fcstValue;

            let weather;

            if (sky == "1") {
                weather = "현재 날씨는 맑은 상태로 야외 스포츠 활동을 해도 좋습니다.";
            }else if( sky == "3") {
                weather = "현재 날씨는 구름이 많은 상태로 비가 내릴 수 있으니 야외 스포츠 활동시 주의하시기 바랍니다.";
            }else if( sky == "4") {
                weather = "현재 날씨는 흐린 상태로 등산 하시는 분은 조심하시기 바랍니다";
            }



            let pty = data.result.response.body.items.item[6].fcstValue;

            let ptyValue;

            if(pty == "0"){
                ptyValue = "맑음";
            }else if(pty == "1"){
                ptyValue = "비";
                weather = "현재 비가 오고 있으니 실내에서 스포츠 모임을 하시기 바랍니다.";
            }else if(pty == "2"){
                ptyValue = "눈/비";
                weather = "현재 눈/비가 오고 있으니 실내에서 스포츠 모임을 하시기 바랍니다.";
            }else if(pty == "3"){
                ptyValue = "눈";
                weather = "현재 눈이 오고 있으니 실내에서 스포츠 모임을 하시기 바랍니다.";
            }else{
                ptyValue = "소나기";
                weather = "현재 소나기가 오고 있으니 잠시 실내에서 스포츠 모임을 하시기 바랍니다.";
            }

            let pop = data.result.response.body.items.item[7].fcstValue + "%";
            let tmp = data.result.response.body.items.item[0].fcstValue + "℃";
            let pcp = data.result.response.body.items.item[9].fcstValue;
            let wsd = data.result.response.body.items.item[4].fcstValue + "m/s";
            let reh = data.result.response.body.items.item[10].fcstValue + "%";

            $("#pty").text(ptyValue);
            $("#sky").text(weather);
            $("#pop").text(pop);
            $("#tmp").text(tmp);
            $("#wsd").text(wsd);
            $("#reh").text(reh);
            $("#pcp").text(pcp);

            let dateTime = data.result.response.body.items.item[0].baseTime;
            let convertedTime = data.result.response.body.items.item[0].baseDate + ", " + convertTimeFormat(dateTime);
            $("#date").text(convertedTime);
        } else {
           console.log("fail == >");
           console.log(data);
        }

    },
    error : function(e, status, xhr, data) {
        console.log("error == >");
        console.log(e);
    }
});

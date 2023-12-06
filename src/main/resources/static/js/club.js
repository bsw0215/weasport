let index = {
		init: function(){
        			$("#btn-save").on("click", ()=>{
        				this.save();
        			});
        			$("#btn-delete").on("click", ()=>{
        				this.deleteById();
        			});
        			$("#btn-update").on("click", ()=>{
        				this.update();
        			});

        },

        save: function(){
        			let data = {
        					title: $("#title").val(),
        					contents: $("#cont").val(),
        					minPerson: $("#minPerson").val(),
        					maxPerson: $("#maxPerson").val(),
        					startDate: $("#startDate").val(),
        					endDate: $("#endDate").val(),
        					description: $("#description").val(),
        					sido: $("#sido").val(),
        					si: $("#si").val(),
        					address: $("#address").val(),
        			};

        			 if(data.title == '') {
                            alert("제목을 입력해주세요.")
                            return
                     }

                     if(data.contents == '') {
                             alert("한줄소개를 입력해주세요.")
                             return
                     }
                     if(data.minPerson == "최소 인원") {
                             alert("최소인원을 입력해주세요.")
                             return
                     }
                     if(data.maxPerson == "최대 인원") {
                             alert("최대인원을 입력해주세요.")
                             return
                     }
                     if(data.startDate == '') {
                            alert("시작일을 입력해주세요.")
                            return
                     }
                     if(data.endDate == '') {
                            alert("마감일을 입력해주세요.")
                            return
                     }
                     if(data.sido == "시/도 선택") {
                            alert("시/도 를 선택해주세요.")
                            return
                     }
                     if(data.si == '') {
                            alert("시를 입력해주세요.")
                            return
                     }
                     if(data.minPerson > data.maxPerson){
                            alert("최대 인원을 최소 인원보다 크게 설정하세요.")
                            return
                     }
                     if(data.startDate > data.endDate){
                            alert("시작일을 마감일보다 앞으로 설정하세요.")
                            return
                     }

        			$.ajax({
        				type: "POST",
        				url: "/api/club",
        				data: JSON.stringify(data),
        				contentType: "application/json; charset=utf-8",
        				dataType: "json"
        			}).done(function(resp){
        				alert("모임 등록을 완료했습니다.");
        				location.href = "/tables";
        			}).fail(function(error){
        				alert(JSON.stringify(error));
        			});
        },
}
index.init();
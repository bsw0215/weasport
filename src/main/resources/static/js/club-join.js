let index = {
        init: function(){
            $("#btn-save").on("click", ()=>{
                this.save();
            });
        },

		save: function(){

			let data = {
			    userId : $("#userId").val(),
			    clubId : $("#clubId").val()
			};

			$.ajax({
				type: "POST",
				url: "/api/member",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("모임 신청을 완료했습니다.");
				location.href = "/clubs/"+data.clubId;
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},

        approve: function(id, clubId){

            $.ajax({
                type: "PUT",
                url: "/api/club/"+id,
            }).done(function(resp){
                alert("참여 승인을 완료했습니다.");
                location.href = "/clubs/"+clubId;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },

        deleteById: function(id, clubId){

            $.ajax({
                type: "DELETE",
                url: "/api/club/"+id,
                dataType: "json"
            }).done(function(resp){
                alert("회원을 내보냈습니다.");
                location.href = "/clubs/"+clubId;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },
}

index.init();

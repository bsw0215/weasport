let index = {
        init: function(){
            $("#btn-save").on("click", ()=>{
                this.save();
            });
            $("#btn-reply-save").on("click", ()=>{
                this.replySave();
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

		deleteById: function(){
			let id = $("#ci").val();
			$.ajax({
				type: "DELETE",
				url: "/api/club/"+id,
				dataType: "json"
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},

        approve: function(id, clubId){

            $.ajax({
                type: "PUT",
                url: "/api/member/"+id,
            }).done(function(resp){
                alert("참여 승인을 완료했습니다.");
                location.href = "/clubs/"+clubId;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },

        deleteMember: function(id, clubId){

            $.ajax({
                type: "DELETE",
                url: "/api/member/"+id,
                dataType: "json"
            }).done(function(resp){
                alert("회원을 내보냈습니다.");
                location.href = "/clubs/"+clubId;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },

		replySave: function(){
			let data = {
					userId: $("#userId").val(),
					clubId: $("#clubId").val(),
					contents: $("#reply-content").val()
			};

			$.ajax({
				type: "POST",
				url: "/api/reply",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("댓글작성이 완료되었습니다.");
				location.href = `/clubs/${data.clubId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},
}

index.init();

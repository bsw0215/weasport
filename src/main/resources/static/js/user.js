let index = {
		init: function(){
			$("#btn-update").on("click", ()=>{ 
				this.update();
			});
		},
		
		update: function(){
			let id = $("#id").val();
			
			let data = {
					name: $("#name").val(),
					email: $("#email").val(),
					address: $("#address").val()
			};

			$.ajax({ 
				type: "PUT",
				url: "/api/user/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("내 정보 수정이 완료되었습니다.");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},

}

index.init();

$(document).ready(function () {
        let id = $("#userId").val();
        $.ajax({
            url: "/api/myTable/"+id,
            type: "GET",
            dataType: "json",
            success: function (data){
                let table = $('#dataTable').DataTable({
                    data: data,
                    columns: [
                            {data: "title"},
                            {data: "startDate"},
                            {data: "endDate"},
                            {data: "sido"},
                            {data: "si"},
                            {data: "clubStatus"}
                    ],
                    autoWidth : true,
                });

            }
        });

});
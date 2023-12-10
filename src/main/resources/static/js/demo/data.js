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
                             {
                                data: "title",
                                render: function(data, type, row) {
                                    if (type === 'display') {

                                        let link = `<a href="/clubs/` + row.id +'">' + data + '</a>';
                                        return link;
                                    }
                                    return data;
                                }
                            },
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
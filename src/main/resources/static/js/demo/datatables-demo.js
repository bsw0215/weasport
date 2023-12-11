// Call the dataTables jQuery plugin

$(document).ready(function () {
        $.ajax({
            url: "/api/tables",
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
                            {data: "contents"},
                            {data: "weather"},
                            {data: "maxPerson"},
                            {data: "startDate"},
                            {data: "endDate"},
                            {data: "sido"},
                            {data: "si"},

                    ],
                    autoWidth : true,
                });
                table.columns([2,3,4,5,6,7]).every(function () {
                            let column = this;
                            // Create select element
                            let select = document.createElement('select');
                            select.add(new Option(''));
                            column.footer().replaceChildren(select);

                            // Apply listener for user change in value
                            select.addEventListener('change', function () {
                                var val = DataTable.util.escapeRegex(select.value);

                                column
                                    .search(val ? '^' + val + '$' : '', true, false)
                                    .draw();
                            });

                            // Add list of options
                            column
                                .data()
                                .unique()
                                .sort()
                                .each(function (d, j) {
                                    select.add(new Option(d));
                                });



                });

            }
        });

});





// Call the dataTables jQuery plugin
//$(document).ready(function() {
//  $('#dataTable').DataTable({
//            bStateSave: true,
//            initComplete: function () {
//                            this.api()
//                                .columns([2,3,4,5,6,7])
//                                .every(function () {
//                                    let column = this;
//
//                                    // Create select element
//                                    let select = document.createElement('select');
//                                    select.add(new Option(''));
//                                    column.footer().replaceChildren(select);
//
//                                    // Apply listener for user change in value
//                                    select.addEventListener('change', function () {
//                                        var val = DataTable.util.escapeRegex(select.value);
//
//                                        column
//                                            .search(val ? '^' + val + '$' : '', true, false)
//                                            .draw();
//                                    });
//
//                                    // Add list of options
//                                    column
//                                        .data()
//                                        .unique()
//                                        .sort()
//                                        .each(function (d, j) {
//                                            select.add(new Option(d));
//                                        });
//                                });
//                        }
//            });
//});


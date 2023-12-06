//// Call the dataTables jQuery plugin
//$(document).ready(function() {
//  $('#dataTable').DataTable();
//});
// Call the dataTables jQuery plugin

var table = null;
window.onload = function () {
    table = $('#dataTable').DataTable({
        processing: true,
        serverSide: true,
        ordering: false,
        ajax: {
            url: "/api/club",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        columns: [
            {data: "title"},
            {data: "contents"},
            {data: "minPerson"},
            {data: "maxPerson"},
            {data: "startDate"},
            {data: "endDate"},
            {data: "sido"},
            {data: "si"},
            {data: "address"}

        ]
    });
}

//$(document).ready(function() {
//  $('#dataTable').DataTable({
//            initComplete: function () {
//                            this.api()
//                                .columns()
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
//                                            var val = $.fn.dataTable.util.escapeRegex(d);
//                                                if (column.search() === "^" + val + "$") {
//                                                  select.append(
//                                                    '<option value="' + d + '" selected="selected">' + d + "</option>"
//                                                  );
//                                                } else {
//                                                  select.append('<option value="' + d + '">' + d + "</option>");
//                                                }
//                                        });
//                                });
//                        }
//            });
//});


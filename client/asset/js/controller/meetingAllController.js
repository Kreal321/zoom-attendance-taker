const nav = new Nav("meeting.all");
nav.render();

const table = $("#meeting-table");

function load_datatable(reload = false) {


    getDataResponse("/meeting/all", (response) => {
        console.log(response);

        const data = response.data;
        data.forEach((item) => {
            item.action = `
                <a class="btn btn-outline-primary" href="/">View</a>
            `;
        });

        if (reload) table.DataTable().destroy();
        table.DataTable({
            autoWidth: false,
            data: data,
            columns: [
                { "data": "meetingUuid", "visible": false },
                { "data": "meetingId", "width": "15%" },
                { "data": "hostId", "visible": false},
                { "data": "topic", "width": "30%" },
                { "data": "type", "width": "10%" },
                { "data": "startTime" },
                { "data": "endTime", },
                { "data": "action", "searchable": false, "className": "text-right" }],
            pagingType: "simple_numbers",
            lengthMenu: [[5, 10, 20, -1], [5, 10, 20, "All"]],
            responsive: true,
            language: {
                emptyTable: "You have no meeting info yet.",
                info: "_MAX_ meetings in total",
                infoEmpty: "You have no meeting info yet.",
                lengthMenu: "_MENU_ meetings per page",
                search: "_INPUT_",
                searchPlaceholder: "Search"
            }
        })
        
        
    })
}

load_datatable();



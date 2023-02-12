const nav = new Nav("meeting.detail");
nav.render();

const table = $("#participant-table");
const cardHolder = document.getElementById("card-holder");

function load_meeting_detail(mid, reload = false) {

    getDataResponse("/meeting/" + mid, (response) => {

        console.log(response);

        const data = response.data;

        cardHolder.innerHTML = "";
        cardHolder.innerHTML += `
            <div class="col-md-12 mb-3">
                <div class="card">
                <div class="card-body p-4">
                    <h5 class="card-title">${data.topic}</h5>
                    <p class="card-text">${convertTimestampToString(data.startTime, type="long")}</p>
                    <span class="badge text-bg-primary p-2">${data.meetingId}</span>
                </div>
                </div>
            </div>
        `;
        cardHolder.innerHTML += new IconCard("Duration", "40 mins", `${convertTimestampToString(data.startTime, type="time")} - ${data.endTime == null ? "Now" : convertTimestampToString(data.endTime, type="time")}`, "people").render();
        cardHolder.innerHTML += new IconCard("Total Participant", data.attendances.length , "No meeting group", "clock-history", "text-warning").render();


        data.attendances.forEach((item) => {
            if (item.duration == -1) {
                item.duration = "In Progress";
            }
            if (item.email == null) {
                item.email = "Visitor";
            }
            item.group = `
                <a class="btn btn-outline-primary" href="/">Group</a>
            `;
            item.action = `
                <a class="btn btn-outline-primary" href="/">Edit</a>
            `;
        });

        if (reload) table.DataTable().destroy();
        table.DataTable({
            autoWidth: false,
            data: data.attendances,
            columns: [
                { "data": "aid", "visible": false },
                { "data": "participant.userName" },
                { "data": "participant.email"},
                { "data": "duration" },
                { "data": "group" },
                { "data": "action", "sortable": false ,"searchable": false, "className": "text-end" }],
            pagingType: "simple_numbers",
            lengthMenu: [[5, 10, 20, -1], [5, 10, 20, "All"]],
            responsive: true,
            language: {
                emptyTable: "This meeting have no attendees yet.",
                info: "_MAX_ attendees in total",
                infoEmpty: "This meeting have no attendees yet.",
                lengthMenu: "_MENU_ attendees per page",
                search: "_INPUT_",
                searchPlaceholder: "Search"
            }
        })
        
        
    })
}


if (urlParams.get('mid') != null) {
    load_meeting_detail(urlParams.get('mid'));
} else {
    swal({
        title: "404 Not Found",
        text: "Incorrect meeting id",
        icon: "error"
    }).then(()=>{
        window.location.href = "/meeting/all.html";
    });
}
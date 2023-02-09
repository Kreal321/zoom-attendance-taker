const roomMessages = document.getElementById("room-messages");
const roomSeats = document.getElementById("room-seats");
var roomName, userName;
const gameJson = {
    numOfPlayers: 6,
    playerList: [new Player("Tom"), null, new Player("Ben"), null, new Player("Hi"), null]
};

const seatList = []; 


// room name validation
function roomNameValidator() {
    roomName = window.localStorage.getItem("roomName");
    if (!roomName) {
        swal({
            title: "错误",
            text: "未加入任何房间",
            icon: "error",
        }).then((value) => {
            window.location.replace("index.html");
            return false;
        });
    }
}

// user name validation
function userNameValidator() {
    userName = window.localStorage.getItem("userName");
    if (!userName) {
        swal({
            title: "加入房间",
            text: "请输入你的游戏昵称",
            content: "input",
            closeOnClickOutside: false,
        }).then((value) => {
            if (!value) {
                swal({
                    title: "错误",
                    text: "游戏昵称不能为空",
                    icon: "error",
                }).then((value) => {
                    userNameValidator();
                });
            } else if ( /\s/g.test(value) ){
                swal({
                    title: "错误",
                    text: "游戏昵称不能包含空格",
                    icon: "error",
                }).then((value) => {
                    userNameValidator();
                });
            } else {
                window.localStorage.setItem("userName", value);
                userName = window.localStorage.getItem("userName");
                connectAndWelcome();
            }
        });
    } else {
        connectAndWelcome();
    }

}


function initializeSeats(game) {
    game.playerList.forEach((player, idx) => {
        seatList[idx] = new Seat(idx + 1, player);
    });
}

function updateRoomSeatDOM() {
    
    roomSeats.innerHTML = "";

    seatList.forEach((seat) => {
        roomSeats.innerHTML += seat.render();
    });

}

initializeSeats(gameJson)
// updateRoomSeatDOM();

setTimeout(updateRoomSeatDOM, 2000);



// subscribe room messages
function roomMessageSubscriber() {

    client.subscribe("/api/message/subscription/" + roomName, payload => {
        roomMessages.innerHTML = `<span class="d-block"> ${JSON.parse(payload.body).message} </span>` + roomMessages.innerHTML;
        // console.log(payload.body);
    });
}

// Take the value in the ‘message-input’ text field and send it to the server with empty headers.
// function sendMessage(message, roomName){
    
//     client.send('/api/message/new/' + roomName, {}, JSON.stringify({message: message}));

// }

// subscribe room messages
function initializeMessaging(retry = 1) {
    try {
        roomMessageSubscriber();
        console.log("成功");
        return true;
    } catch (e) {
        console.log(e.stack);
        roomMessages.innerHTML = `<span class="d-block"> [系统] ${retry} / 3 尝试连接消息服务器 </span>` + roomMessages.innerHTML;
        if (retry < 3) setTimeout(()=>{initializeMessaging(retry + 1)}, 4000);
    }
}


roomNameValidator();
userNameValidator();
// connectAndWelcome();


function connectAndWelcome() {
    // Connect to the server
    setTimeout(()=>{
        if (initializeMessaging()) {
            client.send('/api/message/room/player/new', {}, JSON.stringify({
                "roomName": roomName,
                "userName": userName
            }));
        } else {
            swal({
                title: "网络错误",
                text: "请检查设备网络或服务器已离线",
                icon: "error",
            });
        }
    }, 20);
}

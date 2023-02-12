// const HOST = "http://127.0.0.1:8080";
const HOST = "http://api.kreal.me";

const DOMAIN = "127.0.0.1";

const urlParams = new URLSearchParams(window.location.search);


// Timestamp to String
function convertTimestampToString(ts, type = "normal", tz = "America/New_York") {

    var date = new Date(ts);

    switch (type) {
        case "normal":
            return date.toLocaleString('en-US', { 
                timeZone: tz,
                month: 'short',
                day: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
                timeZoneName: "short",
                weekday: "short"
            });

        case "long":
            return date.toLocaleString('en-US', { 
                timeZone: tz,
                year: 'numeric',
                month: 'short',
                day: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
                timeZoneName: "short",
                weekday: "short"
            });

        case "time":
            return date.toLocaleString('en-US', { 
                timeZone: tz,
                hour: 'numeric',
                minute: 'numeric',
                timeZoneName: "short"
            });

        default:
            return date.toLocaleString('en-US', { 
                timeZone: tz,
                month: 'short',
                day: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
                timeZoneName: "short",
                weekday: "short"
            });

    }



}


// Cookie Helper Functions

function getCookie(name) {
    var cookieArr = document.cookie.split(";");
    
    for(var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");
        
        if(name == cookiePair[0].trim()) {
            return decodeURIComponent(cookiePair[1]);
        }
    }
    
    return null;
}

function setCookie(name, value, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + "; domain=" + DOMAIN + "; path=/";

}

function deleteCookie(name) {
    document.cookie = name + "=; path=/; domain=" + DOMAIN + "; max-age=0";
}

// Fetch Helper Functions

async function getDataResponse(url, callback) {
    let response = await fetch(HOST + url , {
        method: 'GET', 
        mode: 'cors',
        headers: {
            accept: 'application/json',
            authorization: 'Bearer ' + window.localStorage.getItem("token")
        }
    }).catch(error => {
        console.log(error);
        swal({
            title: "Network Error",
            text: "Please try again later",
            icon: "error",
        });
        return;
    });

    const responseBody = await response.json();

    // Failed
    if (response.status != 200) {
        if (response.status == 401) {
            deleteCookie("token");
            swal({
                title: "You are signed out",
                text: "Please login again",
                icon: "error"
            }).then(() => {
                window.location.href = "/user/login.html";
            });
        } else {
            swal({
                title: "Server Error",
                text: "Please try again later",
                icon: "error",
            });
        }

        return;
    } 

    // Success
    callback(responseBody);


}
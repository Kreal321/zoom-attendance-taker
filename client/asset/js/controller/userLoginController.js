const nav = new Nav("user.login");
nav.render();

const urlParams = new URLSearchParams(window.location.search);

async function handleZoomOAuth2Request() {
    let response = await fetch(HOST + "/zoom/oauth2/login" , {
        method: 'GET', 
        mode: 'cors',
        headers: {
            Accept: 'application/json',
        }
    }).catch(error => {
        swal({
            title: "Network Error",
            text: "Please try again later",
            icon: "error",
        });
        return;
    });

    const data = await response.json();
    console.log(data);

    // Failed
    if (!data.success) {
        swal({
            title: "Something went wrong",
            text: "Please try again later",
            icon: "error",
        });
        return;
    }

    // Success
    window.location.href = data.data;

}

async function validateTokenRequest() {
    let response = await fetch(HOST + "/user/auth" , {
        method: 'GET', 
        mode: 'cors',
        credentials: "include",
        headers: {
            Accept: 'application/json',
        }
    }).catch(error => {
        console.log(error);
        swal({
            title: "You are signed out",
            text: "Please login again",
            icon: "error"
        });
        deleteCookie("token");
        return;
    });

    const data = await response.json();
    console.log(data);

    // Failed

    // Success
    // window.location.href = data.data;
    console.log("token validate succeeded");


}

async function getTokenRequest(code) {
    let response = await fetch(HOST + "/zoom/oauth2/token?code=" + code , {
        method: 'GET', 
        mode: 'cors',
        headers: {
            Accept: 'application/json',
        }
    }).catch(error => {
        swal({
            title: "Network Error",
            text: "Please try again",
            icon: "error",
        });
        return;
    });

    const data = await response.json();
    console.log(data);

    // Failed
    if (!data.success) {
        swal({
            title: "Something went wrong",
            text: "Please login again",
            icon: "error"
        });
        return;
    }

    // Success
    setCookie("token", data.data, 1);
    window.location.href = "/";

}


document.getElementById("zoomLoginBtn").addEventListener("click", handleZoomOAuth2Request);

// handle code request
if (urlParams.get('code') != null) {
    getTokenRequest(urlParams.get('code'));
}

// check whether user has logged in
if (getCookie("token") != null) {
    // window.location.href = "/";
}

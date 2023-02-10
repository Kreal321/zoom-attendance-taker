// whether token is exist in cookie
if (getCookie("token") == null) {
    window.location.href = "/user/login.html";
} else {
    getDataResponse("/user/auth", (data) => {

    });
}


// whether token is exist in cookie
if (window.localStorage.getItem("token") == null) {
    window.location.href = "/user/login.html";
} else {
    getDataResponse("/user/auth", (data) => {

    });
}


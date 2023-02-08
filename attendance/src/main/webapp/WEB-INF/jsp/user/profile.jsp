<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../partial/head.html"%>
</head>

<body>

<%@ include file="../partial/header.html"%>

<div class="content-center text-center">

    <main class="form-feedback w-100 m-auto">
        <form>
            <img class="mb-4" src="/resources/img/logo.png" alt="" width="100" height="100">
            <div class="h3 mb-3">Hi <c:out value="${user.getFullName()}" /></div>

            <div class="mb-3 d-flex justify-content-center">
                <div class="badge rounded-pill mx-2 p-2 text-bg-secondary"><c:out value="${question.getCategory_name()}"/></div>
                <c:choose>
                    <c:when test="${user.is_active()}">
                        <div class="badge rounded-pill mx-2 p-2 text-bg-success">Active</div>
                    </c:when>
                    <c:otherwise>
                        <div class="badge rounded-pill mx-2 p-2 text-bg-danger">Suspend</div>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.is_admin()}">
                        <div class="badge rounded-pill mx-2 p-2 text-bg-info">Admin</div>
                    </c:when>
                    <c:otherwise>
                        <div class="badge rounded-pill mx-2 p-2 text-bg-secondary">User</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Feedback Time" value="<c:out value='${user.getUser_id()}' />" required disabled>
                <label>User ID #</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Feedback Time" value="<c:out value='${user.getFirstname()}' />" required disabled>
                <label>User First Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Feedback Time" value="<c:out value='${user.getLastname()}' />" required disabled>
                <label>User Last Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Feedback Time" value="<c:out value='${user.getPhone()}' />" required disabled>
                <label>Contact Phone</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Feedback Time" value="<c:out value='${user.getEmail()}' />" required disabled>
                <label>Contact Email</label>
            </div>

        </form>

        <c:if test="${user == null || !user.is_admin()}">
            <div class="d-flex justify-content-center mt-4">
                <a href="/" type="button" class="btn btn-primary px-4">Home Page</a>
            </div>
        </c:if>

        <p class="mt-4 mb-3 text-muted">© Kason Xu</p>

    </main>

</div>

<%@ include file="../partial/footer.html"%>

</body>

</html>
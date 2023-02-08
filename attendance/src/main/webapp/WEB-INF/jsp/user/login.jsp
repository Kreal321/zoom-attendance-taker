<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="../partial/head.html"%>
    </head>

    <body class="bg-body-secondary h-100">

        <%@ include file="../partial/header.html"%>



        <div class="container my-5">
            <div class="row justify-content-center">

                <div class="col-sm-12 col-md-8 col-lg-6 mb-3">
                    <div class="card">
                        <div class="card-body p-5">

                            <h1 class="h3 mb-3">Welcome back, please log in</h1>
                            <c:if test="${flash_msg != null}">
                                <h1 class="h6 mb-3 fw-normal text-<c:out value="${flash_msg_type}" />"><c:out value="${flash_msg}" /></h1>
                            </c:if>

                            <a class="mt-3 w-100 btn btn-lg btn-primary" href="<c:out value="${authorize_url}" />">
                                Sign in with Zoom
                            </a>

                            <hr class="my-4">
                            <h2 class="fs-5 fw-bold mb-3">What is oAuth2?</h2>

                        </div>
                    </div>


                </div>

                <p class="mt-4 mb-3 text-muted">© Kason Xu</p>

            </div>
        </div>


        <%@ include file="../partial/footer.html"%>

    </body>

</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="login" uri="/WEB-INF/custom/login.tld" %>
<%@ taglib prefix="language" uri="/WEB-INF/custom/language.tld" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="admin.head"/></title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico"/>
    <!-- Core theme CSS (includes Bootstrap)-->
</head>
<body>
<div class="d-flex" id="wrapper">
    <!-- Sidebar-->
    <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light">AMERICAN CARS</div>
        <div class="list-group list-group-flush">
            <!-- Sign in menu start -->
            <login:login user="${sessionScope.user}" avatarName="${sessionScope.avatarName}"
                         contextPath="${pageContext.request.contextPath}" email="${sessionScope.loginErrors.email}"
                         password="${sessionScope.loginErrors.password}"
                         currentLocale="${pageContext.request.locale}"/>
            <c:if test='${!sessionScope.loginErrors.equals(null)}'>
                <p class="text-center" id='login-err-server' style='color: red'>${sessionScope.loginErrors.message}</p>
            </c:if>
            <!-- Sign in menu end -->
            <c:if test="${sessionScope.user == null}">
                <a class="list-group-item list-group-item-action list-group-item-light p-3"
                   href="${pageContext.request.contextPath}/registration"><fmt:message key="menu.registration"/></a>
            </c:if>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="${pageContext.request.contextPath}/catalog"><fmt:message key="menu.catalog"/></a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="${pageContext.request.contextPath}/basket"><fmt:message key="menu.basket"/></a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="${pageContext.request.contextPath}/order"><fmt:message key="menu.orders"/></a>
            <a class="list-group-item list-group-item-action list-group-item-light p-3"
               href="${pageContext.request.contextPath}/profile"><fmt:message key="menu.profile"/></a>
        </div>
    </div>
    <!-- Page content wrapper-->
    <div id="page-content-wrapper">
        <!-- Top navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="container-fluid">
                <button class="btn btn-primary" id="sidebarToggle"><fmt:message key="nav.menu"/></button>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                        <li class="nav-item active"><a class="nav-link"
                                                       href="${pageContext.request.contextPath}/index.jsp">
                            <fmt:message key="nav.home"/></a></li>
                        <li class="nav-item"><a class="nav-link" href="#!"><fmt:message key="nav.contacts"/></a></li>
                        <language:language locales="${sessionScope.locales}"
                                           currentLocale="${pageContext.request.locale}"
                                           uriForPagination=""/>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container-fluid">
            <h1 class="mt-4"><fmt:message key="successful.registration.heading"/>,
                <fmt:message key="admin.admin"/> ${sessionScope.user.firstName} ${sessionScope.user.secondName}!</h1>
            <br>
            <p><fmt:message key="admin.p1"/></p>
            <p><fmt:message key="admin.p2"/></p>
        </div>
        <div class="container-fluid">
            <!-- Copyright -->
            <div class="container-fluid">
                <div class="text-center">
                    <div class="container-sm">
                        <a href="http://www.freepik.com">Designed by pch.vector / Freepik</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jQueryScriptIndex.js"></script>
</body>
</html>
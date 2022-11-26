<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="captcha" uri="/WEB-INF/custom/captcha.tld" %>
<%@ taglib prefix="language" uri="/WEB-INF/custom/language.tld" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="registration.head"/></title>
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
            <h1 class="mt-4"><fmt:message key="registration.heading"/></h1>
            <p><fmt:message key="registration.p1"/></p>
            <form class="form-signin" name="form-signin" action="${pageContext.request.contextPath}/registration"
                  method="post" id="contact-form" enctype="multipart/form-data">
                <div class="col-md-6">
                    <label for="firstName"><fmt:message key="registration.first.name"/><span style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"><fmt:message
                                key="registration.first.name.example"/></span>
                    </label>
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           value="${sessionScope.userDTO.firstName}" required>
                    <p id="firstName-err" style="display:none"><fmt:message key="registration.first.name.error"/></p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.firstName != null}">
                        <p id="firstName-err-server" style="color: red">${sessionScope.userValidationErr.firstName}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="secondName"><fmt:message key="registration.second.name"/><span
                            style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"><fmt:message
                                key="registration.second.name.example"/></span>
                    </label>
                    <input type="text" class="form-control" name="secondName" id="secondName"
                           value="${sessionScope.userDTO.secondName}" required>
                    <p id="secondName-err" style="display:none"><fmt:message key="registration.second.name.error"/></p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.secondName != null}">
                        <p id="secondName-err-server"
                           style="color: red">${sessionScope.userValidationErr.secondName}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="email"><fmt:message key="registration.email"/><span style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px">youremail@gmail.com</span>
                    </label>
                    <input type="text" class="form-control" name="email" id="email"
                           value="${sessionScope.userDTO.email}" required>
                    <p id="email-err" style="display:none"><fmt:message key="registration.email.example"/>
                        'email@gmail.com'</p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.email != null}">
                        <p id="email-err-server" style="color: red">${sessionScope.userValidationErr.email}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="phone"><fmt:message key="registration.phone.number"/><span style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"><fmt:message
                                key="registration.phone.example"/></span>
                    </label>
                    <input type="text" class="form-control" name="phone" id="phone"
                           value="${sessionScope.userDTO.phoneNumber}" required>
                    <p id="phone-err" style="display:none"><fmt:message key="registration.phone.error"/></p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.phone != null}">
                        <p id="phone-err-server" style="color: red">${sessionScope.userValidationErr.phone}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="birthDate"><fmt:message key="registration.birthdate"/><span style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"><fmt:message
                                key="registration.birthdate.example"/> -> 12.31.2000</span>
                    </label>
                    <input type="text" class="form-control" name="birthDate" id="birthDate"
                           value="${sessionScope.userDTO.birthDate}" required>
                    <p id="birthDate-err" style="display:none"><fmt:message key="registration.birthdate.error"/>
                        '01.01.2000'</p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.birthDate != null}">
                        <p id="birthDate-err-server" style="color: red">${sessionScope.userValidationErr.birthDate}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="password"><fmt:message key="registration.password"/><span style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"> <fmt:message
                                key="registration.password.example"/>
                            -> D1231231</span>
                    </label>
                    <input type="password" class="form-control" name="password" id="password"
                           value="${sessionScope.userDTO.password}" required>
                    <p id="password-err" style="display:none"><fmt:message key="registration.password.error"/></p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.password != null}">
                        <p id="password-err-server" style="color: red">${sessionScope.userValidationErr.password}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label for="repeatPass"><fmt:message key="registration.repeat.password"/><span
                            style="color:red">*</span>
                        <span style="color:lightgrey; font-size:12px"> <fmt:message
                                key="registration.repeat.password.example"/></span></label>
                    <input type="password" class="form-control" name="repeatPass" id="repeatPass"
                           value="${sessionScope.userDTO.repeatPassword}" required>
                    <p id="repeatPass-err" style="display:none"><fmt:message
                            key="registration.repeat.password.error"/></p>
                    <!-- server response -->
                    <c:if test="${sessionScope.userValidationErr.repeatPass != null}">
                        <p id="repeatPass-err-server"
                           style="color: red">${sessionScope.userValidationErr.repeatPass}</p>
                    </c:if>
                </div>
                <!-- avatar field start -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="avatar"><fmt:message key="registration.avatar"/>:</label>
                        <input type="file" name="avatar" class="form-control"
                               placeholder="<fmt:message key="registration.avatar.placeholder"/>" id="avatar"
                               accept=".jpg">
                    </div>
                </div>
                <!-- avatar field end -->
                <!-- subscription checkbox start -->
                <div class="form-check">
                    <input class="form-check-input" name="subscription" type="checkbox" value="1" id="flexCheckDefault">
                    <c:choose>
                        <c:when test="${sessionScope.userDTO.subscription != null}">
                            <input class="form-check-input" name="subscription" type="checkbox" value="1"
                                   id="flexCheckDefault" checked>
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" name="subscription" type="checkbox" value="1"
                                   id="flexCheckDefault">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="flexCheckDefault">
                        <fmt:message key="registration.subscription"/>
                    </label>
                </div>
                <!-- subscription checkbox end -->
                <!-- captcha start -->
                <div>
                    <captcha:captcha captchaId="${sessionScope.captchaId}"
                                     captchaLocation="${applicationScope['captchaLocation'.toString()]}"/>
                    <br/>
                    <label for="captchaCode"><fmt:message key="registration.captcha.description"/></label>
                    <br/>
                    <input type="text" name="captchaCode" id="captchaCode"/>
                    <input type="submit" name="refresh" value="<fmt:message key="registration.captcha.refresh"/>"
                           id="refresh"/>
                    <c:if test="${sessionScope.userValidationErr.userCaptchaMap != null}">
                        <p id="captcha-err" style="color: red">${sessionScope.userValidationErr.userCaptchaMap}</p>
                    </c:if>
                </div>
                <!-- captcha end -->
                <hr>
                <p>
                    <input type="submit" name="submit" value="<fmt:message key="registration.submit"/>" id="submit">
                </p>
            </form>
        </div>
        <br>
        <!-- Copyright -->
        <div class="text-center">
            <div class="container-sm">
                <a href="http://www.freepik.com">Designed by pch.vector / Freepik</a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<!-- jQuery lib -->
<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<!-- jQuery check -->
<script src="${pageContext.request.contextPath}/js/jQueryScriptRegistration.js"></script>
</body>
</html>
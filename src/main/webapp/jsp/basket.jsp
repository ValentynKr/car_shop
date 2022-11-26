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
    <title><fmt:message key="basket.head"/></title>
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
            <h1 class="mt-4"><fmt:message key="basket.heading"/></h1>
            <p><fmt:message key="basket.p1"/></p>
            <!-- Errors -->
            <c:choose>
                <c:when test="${sessionScope.orderError != null}">
                    <div class="alert alert-danger text-center"><strong><fmt:message
                            key="basket.order.not.formed"/></strong></div>
                    <c:forEach var="error" items="${sessionScope.orderError}">
                        <div class="alert alert-danger text-center">${error}</div>
                    </c:forEach>
                </c:when>
            </c:choose>
            <!-- Table of results start -->
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${sessionScope.basket == null}">
                        <h1 class="mt-4"><fmt:message key="basket.nothing.found"/></h1>
                        <p><fmt:message key="basket.nothing.found.advice"/></p>
                    </c:when>
                    <c:when test="${sessionScope.basket.carQuantityFromBasket == 0}">
                        <h1 class="mt-4"><fmt:message key="basket.nothing.found"/></h1>
                        <p><fmt:message key="basket.nothing.found.advice"/></p>
                    </c:when>
                    <c:otherwise>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message key="catalog.car.table.photo"/></th>
                                <th><fmt:message key="catalog.car.table.name"/></th>
                                <th><fmt:message key="catalog.car.table.parameters"/></th>
                                <th><fmt:message key="basket.car.table.quantity"/></th>
                                <th><fmt:message key="basket.car.table.remove.button"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="car" items="${sessionScope.basket.carsFromBasket}">
                                <tr>
                                    <td><img src="${pageContext.request.contextPath}/assets/cars/${car.imageName}"
                                             alt="${car.imageName}"/></td>
                                    <td><h3>${car.name} ${car.manufacturer.name}</h3></td>
                                    <td><h4><fmt:message key="catalog.car.cell.price"/>: <fmt:message
                                            key="catalog.car.cell.dollar"/>${car.price} <fmt:message
                                            key="catalog.car.cell.dollar.after"/></h4>
                                        <h6><fmt:message key="catalog.car.cell.year"/>: ${car.productionYear}</h6>
                                        <h6><fmt:message key="catalog.car.cell.mileage"/>: ${car.mileage} <fmt:message
                                                key="catalog.car.cell.km"/></h6>
                                        <h6><fmt:message key="catalog.car.cell.category"/>: ${car.category.name}</h6>
                                        <h6><fmt:message key="catalog.car.cell.volume"/>: ${car.engineVolume}
                                            <fmt:message key="catalog.car.cell.l"/></h6>
                                    </td>
                                    <td>
                                        <h6>${sessionScope.basket.getCarQuantity(car)}</h6>
                                        <button type="button" class="btn btn-outline-primary basket-q-less"
                                                data-id="${car.id}"
                                                style="width: 38px">-
                                        </button>
                                        <button type="button" class="btn btn-outline-primary basket-q-more"
                                                data-id="${car.id}"
                                                style="width: 38px">+
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-outline-danger basket-remove"
                                                data-id="${car.id}"><fmt:message key="basket.car.table.remove.button"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="text-center">
                            <!-- Total data -->
                            <h3><fmt:message key="basket.total.price"/>: <fmt:message key="catalog.car.cell.dollar"/>${sessionScope.basket.totalCarCostFromBasket}
                                <fmt:message key="catalog.car.cell.dollar.after"/></h3>
                            <h3><fmt:message
                                    key="basket.total.quantity"/>: ${sessionScope.basket.carQuantityFromBasket}</h3>
                            <!-- Order modal button -->
                            <button type="button" class="btn btn-primary order-button" data-bs-toggle="modal"
                                    data-bs-target="#orderModal"><fmt:message key="basket.order.button"/>
                            </button>
                            <!-- Skip order button -->
                            <a href="${pageContext.request.contextPath}/basket?cancel=1"
                               class='btn btn-outline-danger'>
                                <fmt:message key="basket.remove.all.button"/></a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <!-- Table of results end -->
            <br>
            <!-- Modal start-->
            <div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                    key="basket.modal.heading"/></h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="form-control" method="post" action="${pageContext.request.contextPath}/order"
                                  id="order-form">
                                <label for="payment"><fmt:message key="basket.modal.payment"/>:
                                    <span style="color:lightgrey; font-size:12px"><fmt:message
                                            key="basket.modal.payment.hint"/></span>
                                </label>
                                <select class="form-control" name="payment" id="payment">
                                    <c:choose>
                                        <c:when test="${sessionScope.orderDetail.typeOfPayment == 'CARD'}">
                                            <option selected value="CARD"><fmt:message
                                                    key="basket.modal.payment.card"/></option>
                                            <option value="CASH"><fmt:message key="basket.modal.payment.cash"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="CARD"><fmt:message key="basket.modal.payment.card"/></option>
                                            <option selected value="CASH"><fmt:message
                                                    key="basket.modal.payment.cash"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <label for="credential"><fmt:message key="basket.modal.credentials"/>:
                                    <span style="color:lightgrey; font-size:12px">0000111122223333</span>
                                </label>
                                <input type="text" class="form-control card-cred" name="credential" id="credential"
                                       placeholder="<fmt:message key="basket.modal.card.number.placeholder"/>"
                                       value="${sessionScope.orderDetail.cardCredential}"/>
                                <label for="expDate"><fmt:message key="basket.modal.exp.date"/>:
                                    <span style="color:lightgrey; font-size:12px">12/22, 01/23, 05\24</span>
                                </label>
                                <input type="text" class="form-control card-cred" name="expDate" id="expDate"
                                       placeholder="<fmt:message key="basket.modal.exp.date"/>"
                                       value="${sessionScope.orderDetail.expiringDate}"/>
                                <label for="cvv">CVV <fmt:message key="basket.modal.code"/>:
                                    <span style="color:lightgrey; font-size:12px">360, 012, 236</span>
                                </label>
                                <input type="password" class="form-control card-cred" name="cvv" id="cvv"
                                       placeholder="CVV <fmt:message key="basket.modal.code"/>"
                                       value="${sessionScope.orderDetail.cvvCode}"/>
                                <label for="delivery"><fmt:message key="basket.modal.delivery"/>:
                                    <span style="color:lightgrey; font-size:12px"><fmt:message
                                            key="basket.modal.delivery.description"/></span>
                                </label>
                                <select class="form-control" name="delivery" id="delivery">
                                    <c:choose>
                                        <c:when test="${sessionScope.orderDetail.typeOfDelivery == 'EXW'}">
                                            <option selected>EWX</option>
                                            <option>CPT</option>
                                            <option>FCA</option>
                                            <option>DDP</option>
                                        </c:when>
                                        <c:when test="${sessionScope.orderDetail.typeOfDelivery == 'CPT'}">
                                            <option>EWX</option>
                                            <option selected>CPT</option>
                                            <option>FCA</option>
                                            <option>DDP</option>
                                        </c:when>
                                        <c:when test="${sessionScope.orderDetail.typeOfDelivery == 'FCA'}">
                                            <option>EWX</option>
                                            <option>CPT</option>
                                            <option selected>FCA</option>
                                            <option>DDP</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>EWX</option>
                                            <option>CPT</option>
                                            <option>FCA</option>
                                            <option selected>DDP</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <label for="address"><fmt:message key="basket.modal.delivery.address"/>:
                                    <span style="color:lightgrey; font-size:12px"><fmt:message
                                            key="basket.modal.delivery.address.example"/></span>
                                </label>
                                <input type="text" class="form-control" name="address" id="address"
                                       placeholder="<fmt:message key="basket.modal.delivery.address"/>"
                                       value="${sessionScope.orderDetail.addressForDelivery}"/>
                                <br>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary" id="basket-add"
                                            data-bs-dismiss="modal"><fmt:message key="basket.order.button"/>
                                    </button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                            key="catalog.modal.cancel.button"/>
                                    </button>
                                </div>
                                <br>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal end -->
            <br>
            <!-- Copyright -->
            <div class="text-center">
                <div class="container-sm">
                    <a href="http://www.freepik.com">Designed by pch.vector / Freepik</a>
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
<!-- jQuery script -->
<script src="${pageContext.request.contextPath}/js/jQueryScriptBasket.js"></script>
</body>
</html>
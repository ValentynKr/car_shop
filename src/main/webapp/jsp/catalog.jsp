<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="login" uri="/WEB-INF/custom/login.tld" %>
<%@ taglib prefix="pagination" uri="/WEB-INF/custom/pagination.tld" %>
<%@ taglib prefix="language" uri="/WEB-INF/custom/language.tld" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="catalog.head"/></title>
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
                                           uriForPagination="${requestScope.uriForPagination}"/>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container-fluid">
            <h1 class="mt-4"><fmt:message key="catalog.heading"/></h1>
            <p><fmt:message key="catalog.p1"/></p>
            <!-- Filter menu start -->
            <div class="col-md-12">
                <form class="form-control" method="get" action="${pageContext.request.contextPath}/catalog"
                      id="filter-form">
                    <div class="form-group">
                        <label for="carName" class="mb-1"><fmt:message key="catalog.car.name"/>:</label>
                        <input type="text" class="form-control filter-param" name="name" id="carName"
                               value="${requestScope.filterParams.name}"
                               placeholder="<fmt:message key="catalog.car.name.placeholder"/>"/>
                    </div>
                    <div class="form-group">
                        <label for="carManufacturer" class="mb-1"><fmt:message key="catalog.car.manufacturer"/>:</label>
                        <select class="form-control" name="manufacturer" id="carManufacturer">
                            <c:choose>
                                <c:when test="${requestScope.filterParams.manufacturer == null}">
                                    <option selected value="Not selected"><fmt:message
                                            key="catalog.car.manufacturer.not.selected"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Not selected"><fmt:message
                                            key="catalog.car.manufacturer.not.selected"/></option>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="manufacturer" items="${sessionScope.manufacturerList}">
                                <c:choose>
                                    <c:when test="${manufacturer.name eq requestScope.filterParams.manufacturer}">
                                        <option selected>${manufacturer.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${manufacturer.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="carCategory" class="mb-1"><fmt:message key="catalog.car.category"/>:</label>
                        <select multiple class="form-control" name="category" id="carCategory">
                            <c:forEach var="category" items="${sessionScope.categoryList}">
                                <c:choose>
                                    <c:when test="${requestScope.filterParams.category.contains(category.name)}">
                                        <option selected>${category.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${category.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-6">
                                <label for="minPrice"><fmt:message key="catalog.car.price.from"/>:</label>
                                <input type='number' class="form-control filter-param ml-0 pl-1 mr-0 pr-0"
                                       name='minPrice' min="0" max="100000000"
                                       value="${requestScope.filterParams.minPrice}" id='minPrice' step='100'>
                            </div>
                            <div class='col-6 mb-2'>
                                <label for="maxPrice"><fmt:message key="catalog.car.price.to"/>:</label>
                                <input type='number' class="form-control filter-param ml-0 pl-1 mr-0 pr-0"
                                       name='maxPrice' min="0" max="100000000"
                                       value="${requestScope.filterParams.maxPrice}" id='maxPrice' step='100'>
                            </div>
                            <input type="hidden" name="sortBy" value="${requestScope.filterParams.sortBy}">
                            <input type="hidden" name="pageSize" value="${requestScope.filterParams.pageSize}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <button type='submit' class='btn btn-outline-primary' style="width: 100px"><fmt:message
                                    key="catalog.car.filter.button"/></button>
                            <a href="${pageContext.request.contextPath}/catalog?cancel=1" class='btn btn-outline-danger'
                               style="width: 100px"><fmt:message key="catalog.car.cancel.button"/></a>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Filter menu end -->
            <!-- Sort menu start -->
            <div class="col-md-12">
                <form class="form-control" method="get" action="${pageContext.request.contextPath}/catalog"
                      id="sort-form">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="sortBy" class='mb-1'><fmt:message key="catalog.car.sort"/>:</label>
                                <select type='select' class="form-control form-control-sm sort-param" name='sortBy'
                                        id="sortBy">
                                    <c:choose>
                                        <c:when test="${requestScope.filterParams.sortBy == null}">
                                            <option selected value="Not selected"><fmt:message
                                                    key="catalog.car.manufacturer.not.selected"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Not selected"><fmt:message
                                                    key="catalog.car.manufacturer.not.selected"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="sortBy" items="${sessionScope.sortingParams}">
                                        <c:choose>
                                            <c:when test="${sortBy.key eq requestScope.filterParams.sortBy}">
                                                <option selected value='${sortBy.key}'>${sortBy.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value='${sortBy.key}'>${sortBy.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="pageSize"><fmt:message key="catalog.car.on.page"/>:</label>
                                <c:choose>
                                    <c:when test="${requestScope.filterParams.pageSize == null}">
                                        <input type='number'
                                               class="form-control filter-param sort-param ml-0 pl-1 mr-0 pr-0"
                                               name='pageSize' min="1" max="20" value="10" id='pageSize' step='1'>
                                    </c:when>
                                    <c:otherwise>
                                        <input type='number'
                                               class="form-control filter-param sort-param ml-0 pl-1 mr-0 pr-0"
                                               name='pageSize' min="1" max="20"
                                               value="${requestScope.filterParams.pageSize}" id='pageSize' step='1'>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Sort menu end -->
            <br/>
            <!-- Pagination start -->
            <pagination:pagination uriForPagination="${requestScope.uriForPagination}"
                                   numberOfPages="${sessionScope.numberOfPages}"
                                   page="${requestScope.filterParams.page}"
                                   currentLocale="${pageContext.request.locale}"/>
            <!-- Pagination end -->
            <br/>
            <!-- Table of results start -->
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${requestScope.carList.size() == 0}">
                        <h1 class="mt-4"><fmt:message key="catalog.car.not.found"/></h1>
                        <p><fmt:message key="catalog.car.not.found.advice"/></p>
                    </c:when>
                    <c:otherwise>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message key="catalog.car.table.photo"/></th>
                                <th><fmt:message key="catalog.car.table.name"/></th>
                                <th><fmt:message key="catalog.car.table.parameters"/></th>
                                <th><fmt:message key="catalog.car.table.add"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="car" items="${requestScope.carList}">
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
                                        <!-- Modal button -->
                                        <button type="button" class="btn btn-primary basket-button"
                                                data-bs-toggle="modal"
                                                data-bs-target="#addModal" data-id="${car.id}"
                                                data-name="${car.name} ${car.manufacturer.name}"><fmt:message
                                                key="catalog.car.cell.add"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
            <!-- Table of results end -->
            <!-- Pagination start -->
            <pagination:pagination uriForPagination="${requestScope.uriForPagination}"
                                   numberOfPages="${sessionScope.numberOfPages}"
                                   page="${requestScope.filterParams.page}"
                                   currentLocale="${pageContext.request.locale}"/>

            <!-- Pagination end -->
            <br>
            <!-- Modal start-->
            <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                    key="catalog.car.modal.confirm"/></h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p id='basketCarName'></p>
                            <label for="quantity"><fmt:message key="catalog.car.modal.number"/>:</label>
                            <input type='number' class="form-control ml-0 pl-1 mr-0 pr-0"
                                   name='quantity' min="1" max="10"
                                   value='1' id='quantity' step='1'>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="basket-add" data-bs-dismiss="modal"
                                    data-url="http://localhost:8080/car_shop_war_exploded/basket"><fmt:message
                                    key="catalog.car.cell.add"/></button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                    key="catalog.modal.cancel.button"/></button>
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
<script src="${pageContext.request.contextPath}/js/jQueryScriptRegistration.js"></script>
<script src="${pageContext.request.contextPath}/js/jQueryScriptBasket.js"></script>
<script src="${pageContext.request.contextPath}/js/sortingCatalog.js"></script>
</body>
</html>
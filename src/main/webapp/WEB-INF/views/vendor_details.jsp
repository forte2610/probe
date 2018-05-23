<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Vendor details of ${vendor.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="../css/vendor_details.css" rel="stylesheet" type="text/css"></link>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"
            integrity="sha384-xymdQtn1n3lH2wcu0qhcdaOpQwyoarkgLVxC/wZ5q7h9gHtxICrpcaSUfygqZGOe"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="container main-content">
    <div class="row header">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <a href="/"><img class="img-fluid logo" src="../images/coming.png" alt=""></a>
        </div>
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
        </div>
    </div>
    <div class="navigation-bar">
        <nav class="navbar navbar-expand-lg filter-nav">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#filter-bar"
                    aria-controls="filter-bar" aria-expanded="false" aria-label="Advanced tools">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="filter-bar">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/vendors"><i class="fas fa-chevron-circle-left"></i> All vendors</a>
                    </li>
                    <p class="navbar-text filter-bar-text"> / ${vendor.name}</p>
                </ul>
            </div>
        </nav>
    </div>
    <div class="row title-bar">
        <div class="col-4"><img class="img-fluid vendor-logo" src="../logos/${vendor.logo}" alt="${vendor.name}"></div>
        <div class="col-8"></div>
    </div>
    <div class="row vendor-info">
        <div class="col-md-12">
            <h3 class="vendor-name">${vendor.name}</h3>
            <p class="section-title">OVERVIEW</p>
            <p>${vendor.description}</p>
            <p class="section-title">CONTACT</p>
            <p><i class="content-icon fas fa-link"></i> ${vendor.url}</p>
            <p><i class="content-icon fas fa-phone"></i> 028.386.33333</p>
            <p><i class="content-icon fas fa-envelope"></i> cskh@thegioididong.com</p>
            <p class="section-title">REPUTATION</p>
            <p><i class="content-icon fas fa-star"></i> ${review_score} (based on ${review_count} reviews)</p>
            <hr class="separator">
            <p class="section-title">USER REVIEWS</p>
        </div>
    </div>

    <c:if test="${review_count > 0}">
        <c:forEach items="${reviews}" var="review">
            <div class="row review">
                <div class="col-md-12">
                    <p><b>${review.author.username}</b></p>
                    <fmt:parseDate value="${review.timestamp}" var="parsedTimestamp" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <small>
                        <c:choose>
                            <c:when test="${(review.score) % 2 == 0}">
                                <c:forEach var="i" begin="0" end="${review.score}">
                                    <c:if test="${i > 0}">
                                        <i class="fas fa-star"></i>
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="i" begin="0" end="${5 - review.score}">
                                    <c:if test="${i > 0}">
                                        <i class="far fa-star"></i>
                                    </c:if>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" begin="0" end="${review.score - 0.5}">
                                    <c:if test="${i > 0}">
                                        <i class="fas fa-star"></i>
                                    </c:if>
                                </c:forEach>
                                <i class="fas fa-star-half"></i>
                                <c:forEach var="i" begin="0" end="${4.5 - review.score}">
                                    <c:if test="${i > 0}">
                                        <i class="far fa-star"></i>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        <fmt:formatDate value="${parsedTimestamp}"
                                        pattern="MMM dd, yyyy"/>
                    </small>
                    <p>${review.content}</p>
                </div>
            </div>
            <c:if test="${review_count > 1}">
                <hr>
            </c:if>
        </c:forEach>
    </c:if>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
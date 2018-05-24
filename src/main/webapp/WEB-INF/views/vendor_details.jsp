<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Vendor details of ${vendor.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="../css/common.css" rel="stylesheet" type="text/css"></link>
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
            <c:choose>
                <c:when test="${review_count > 0}">
                    <p><i class="content-icon fas fa-star"></i> ${review_score} (based on ${review_count} reviews)</p>
                </c:when>
                <c:otherwise>
                    <p>There are no user reviews for this vendor yet.</p>
                </c:otherwise>
            </c:choose>

        </div>
    </div>

    <hr class="separator">

    <div class="row vendor-info">
        <div class="col-md-12">
            <p class="section-title">RATE THIS VENDOR</p>
        </div>
    </div>


    <c:choose>
        <c:when test="${is_loggedin == true}">
            <div class="row review-area">
                <div class="col-md-2 offset-md-2 userinfo">
                    <div class="row"><img class="img-fluid" src="../images/user_icon.png"></div>
                    <div class="row login-name">Logged in as&nbsp;<b>${user_name}</b></div>
                </div>
                <div class="col-md-6">
                    <form:form action="/submit-review?id=${vendor.id}" method="post"
                               modelAttribute="newReview" autocomplete="off" class="form-horizontal">
                        <div class="form-group">
                            <label class="helios-label" for="score">Give this vendor a score:</label>
                            <form:input type="number" name="score" path="score" value="2.5"
                                        min="1" max="5" step="0.5" class="form-control helios-textbox"/>
                        </div>

                        <div class="form-group">
                            <label class="helios-label" for="content">Leave a comment:</label>
                            <form:textarea type="text" rows="5" name="content" path="content" placeholder="Content"
                                           class="form-control helios-textbox"/>
                        </div>

                        <div class="form-group">
                            <div class="float-right">
                                <button type="submit" class="btn btn-primary btn-block review-button">Submit review
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row vendor-info">
                <div class="col-md-12">
                    <p>You need to log in to submit a review.</p>
                    <a href="/login" class="btn btn-primary btn-block review-button login-button">Log in</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <c:if test="${review_count > 0}">
        <c:set var="pageListHolder" value="${reviewList}" scope="session"/>

        <hr class="separator">

        <div class="row vendor-info">
            <div class="col-md-12">
                <p class="section-title">USER REVIEWS</p>
            </div>
        </div>

        <c:forEach items="${pageListHolder.pageList}" var="review">
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
                            </c:otherwise>
                        </c:choose>
                        &nbsp;&nbsp;
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

    <c:if test="${pageListHolder.pageCount != null && pageListHolder.pageCount>1}">
        <div class="row vendor-info">
            <div class="helios-pagination">
                <a class="page-link" href="/vendor-details?id=${vendor.id}&p=0">First</a>
                <c:choose>
                    <c:when test="${pageListHolder.firstPage}"><div class="page-link page-link-active">&lt;</div></c:when>
                    <c:otherwise><a class="page-link" href="/vendor-details?id=${vendor.id}&p=prev">&lt;</a></c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageListHolder.page > 2}">
                        ...
                        <c:forEach begin="${pageListHolder.page-3}" end="${pageListHolder.page-1}" varStatus="loop">
                            <a class="page-link" href="/vendor-details?id=${vendor.id}&p=${loop.index}">${loop.index+1}</a>
                        </c:forEach>
                        <a class="page-link" href="/vendor-details?id=${vendor.id}&p=${pageListHolder.page}">${pageListHolder.page+1}</a>
                        <c:forEach begin="${pageListHolder.page+1}" end="${pageListHolder.page+3}" varStatus="loop">
                            <a class="page-link" href="/vendor-details?id=${vendor.id}&p=${loop.index}">${loop.index+1}</a>
                        </c:forEach>
                        ...
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${pageListHolder.pageCount > 1 && pageListHolder.pageCount < 6}">
                                <c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index == pageListHolder.page}"><div class="page-link page-link-active">${loop.index+1}</div></c:when>
                                        <c:otherwise><a class="page-link" href="/vendor-details?id=${vendor.id}&p=${loop.index}">${loop.index+1}</a></c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach begin="0" end="5" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index == pageListHolder.page}"><div class="page-link page-link-active">${loop.index+1}</div></c:when>
                                        <c:otherwise><a class="page-link" href="/vendor-details?id=${vendor.id}&p=${loop.index}">${loop.index+1}</a></c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                ...
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageListHolder.lastPage}"><div class="page-link page-link-active">&gt;</div></c:when>
                    <c:otherwise><a class="page-link" href="/vendor-details?id=${vendor.id}&p=next">&gt;</a></c:otherwise>
                </c:choose>
                <a class="page-link" href="/vendor-details?id=${vendor.id}&p=${pageListHolder.pageCount-1}">Last</a>
            </div>
        </div>
    </c:if>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
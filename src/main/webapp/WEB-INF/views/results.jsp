<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Search results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="css/results.css" rel="stylesheet" type="text/css"></link>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.12/js/all.js" integrity="sha384-Voup2lBiiyZYkRto2XWqbzxHXwzcm4A5RfdfG6466bu5LqjwwrjXCMBQBLMWh7qR" crossorigin="anonymous"></script>
</head>

<body>
<div class="container bootstrap">
    <div class="row header">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <a href="/"><img class="img-fluid logo" src="images/coming.png" alt=""></a>
        </div>
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
            <form class="header-form" action="search" method="get">
                <div class="input-group search-group">
                    <input type="text" class="form-control search-box" name="q" id="q" placeholder="${resultDetails.keyword}">
                    <span class="input-group-btn">
						<button type="submit" class="btn btn-lg search-button">
							<i class="fa fa-search"></i> Search
						</button>
					</span>
                </div>
            </form>
        </div>
    </div>

    <div class="filter-bar">
        <nav class="navbar navbar-expand-lg filter-nav">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#filter-bar" aria-controls="filter-bar" aria-expanded="false" aria-label="Advanced tools">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="filter-bar">
                <ul class="navbar-nav mr-auto">
                    <p class="navbar-text filter-bar-text">Filter results:</p>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Type
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Vendor
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Price range
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <p class="navbar-text filter-bar-text">Found ${resultDetails.count} results for
                        &quot;${resultDetails.keyword}&quot;.</p>
                </ul>
            </div>
        </nav>
    </div>

    <c:set var="pageListHolder" value="${resultList}" scope="session"/>

    <c:forEach var="product" items="${pageListHolder.pageList}">
        <div class="row">
            <div class="well search-result">
                <a class="row result-item" href="${product.vendorURL}">
                    <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                        <img class="img-fluid" src="${product.images}" alt="">
                    </div>
                    <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                        <h3>${product.name}</h3>
                        <p class="price"><fmt:formatNumber pattern="#,##0" value="${product.price}" /><sup>&#8363;</sup></p>
                        <p>${product.description}</p>
                        <p><small>${product.vendorURL}</small></p>
                        <c:choose>
                            <c:when test="${product.type == 'Phone'}">
                                <span class="badge badge-primary"><i class="fas fa-mobile-alt"></i>&nbsp;&nbsp;Mobile phone</span>
                            </c:when>
                            <c:when test="${product.type == 'Laptop'}">
                                <span class="badge badge-primary"><i class="fas fa-laptop"></i>&nbsp;&nbsp;Laptop</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-primary">No type specified</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
            </div>
        </div>
    </c:forEach>

    <a href="/search?q=${resultDetails.keyword}&p=prev" class="btn btn-default">Back</a>
    <a href="/search?q=${resultDetails.keyword}&p=next" class="btn btn-default">Next</a>

</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Search results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="css/results.css" rel="stylesheet" type="text/css"></link>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="container bootstrap snippet">
    <div class="row header">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <img class="img-fluid logo" src="images/coming.png" alt="">
        </div>
        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
            <div class="input-group search-group">
                <input type="text" class="form-control search-box" placeholder="${resultDetails.keyword}">
                <span class="input-group-btn">
						<button class="btn btn-lg search-button" type="button">
							<i class="fa fa-search"></i>
							Search
						</button>
					</span>
            </div>
        </div>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="/">Home</a>
                </li>
                <li class="breadcrumb-item active">
                    Search results
                </li>
                <li class="breadcrumb-item float-right">
                    <a href="" class="text-muted">
                        <i class="fa fa-filter"></i> Filters
                    </a>
                </li>
                <li class="breadcrumb-item float-right">
                    <a href="" class="text-muted">
                        Found ${resultDetails.count} results.
                    </a>
                </li>
            </ol>
        </nav>
    </div>

    <c:set var="pageListHolder" value="${resultList}" scope="session" />

    <hr>

    <c:forEach var="product" items="${pageListHolder.pageList}">
        <div class="row">
            <div class="well search-result">
                <a class="row" href="${product.vendorURL}">
                    <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                        <img class="img-fluid" src="${product.images}" alt="">
                    </div>
                    <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                        <h3>${product.name}</h3>
                        <p class="price">${product.price}₫</p>
                        <p>${product.description}</p>
                        <span class="badge badge-secondary">${product.vendorURL}</span>
                    </div>
                </a>
            </div>
        </div>
    </c:forEach>

</div>
</div>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
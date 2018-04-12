<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Vendors</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>
    <link href="css/results.css" rel="stylesheet" type="text/css"></link>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="container bootstrap snippet">
    <div class="row">
        <nav aria-label="breadcrumb" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="/">Home</a>
                </li>
                <li class="breadcrumb-item active">
                    Vendors
                </li>
            </ol>
        </nav>
    </div>

    <hr>

    <c:forEach  items="${vendors}" var ="vendor">
        <div class="row">
            <div class="well search-result">
                <a class="row" href="/vendor-details/${vendor.id}">
                    <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                        <img class="img-fluid" src="logos/${vendor.logo}" alt="">
                    </div>
                    <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                        <h3>${vendor.name}</h3>
                        <p>${vendor.score} <i class="fas fa-star"></i></p>
                        <p>${vendor.description}</p>
                    </div>
                </a>
            </div>
        </div>
    </c:forEach>

</div>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Vendors</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>
    <link href="css/vendors.css" rel="stylesheet" type="text/css"></link>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
                    <p class="navbar-text filter-bar-text">All vendors</p>
                </ul>
            </div>
        </nav>
    </div>

    <p class="intro-text">Below are the vendors we pull our search results from. Click on a vendor's logo to view their detailed information and submit a review.</p>
    <c:forEach  items="${vendors}" var ="vendor">
        <div class="card vendor-card">
            <a href="/vendor-details?id=${vendor.id}">
                <img class="img-fluid" src="logos/${vendor.logo}" alt="">
            </a>
        </div>
    </c:forEach>

</div>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
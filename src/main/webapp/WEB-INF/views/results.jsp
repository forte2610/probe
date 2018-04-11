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
    <div class="row">
        <div class="well search-result">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-btn">
						<button class="search-button btn btn-light btn-lg" type="button">
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
                    <a href="#">Home</a>
                </li>
                <li class="breadcrumb-item active">
                    Search results
                </li>
                <li class="breadcrumb-item float-right">
                    <a href="" class="text-muted">
                        <i class="fa fa-filter"></i> Filters
                    </a>
                </li>
            </ol>
        </nav>
    </div>

    <hr>

    <div class="row">
        <div class="well search-result">
            <a class="row" href="https://www.thegioididong.com/dtdd/iphone-6-32gb-gold">
                <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                    <img class="img-fluid" src="images/product1.png" alt="">
                </div>
                <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                    <h3>iPhone 6 32GB</h3>
                    <p>8.490.000₫</p>
                    <span class="badge badge-secondary">thegioididong.com</span>
                </div>
            </a>
        </div>
    </div>

    <div class="row">
        <div class="well search-result">
            <a class="row" href="https://www.thegioididong.com/dtdd/iphone-6-64gb">
                <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                    <img class="img-fluid" src="images/product2.png" alt="">
                </div>
                <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                    <h3>iPhone 6 64GB</h3>
                    <p>10.490.000₫</p>
                    <span class="badge badge-secondary">thegioididong.com</span>
                </div>
            </a>
        </div>
    </div>

    <div class="row">
        <div class="well search-result">
            <a class="row" href="https://www.thegioididong.com/dtdd/iphone-6-128gb">
                <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                    <img class="img-fluid" src="images/product3.png" alt="">
                </div>
                <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                    <h3>iPhone 6 128GB</h3>
                    <p>12.490.000₫</p>
                    <span class="badge badge-secondary">thegioididong.com</span>
                </div>
            </a>
        </div>
    </div>

    <div class="row">
        <button type="button" class="btn btn-secondary btn-block">
            <i class="fa fa-refresh"></i> Load more results
        </button>
    </div>

</div>
</div>


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
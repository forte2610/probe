<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Helios | Find your favorite smartphone and PC at the cheapest prices</title>
    <link href="css/home.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='http://fonts.googleapis.com/css?family=Petit+Formal+Script' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Alegreya+Sans:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300' rel='stylesheet' type='text/css'>
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"
            integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <div class="wrap">
        <div class="content-grid">
            <p><img src="images/top.png" title=""></p>
        </div>
        <div class="grid">
            <p><img class="main_logo" src="images/coming.png" title=""></p>

            <form action="search" method="get">
                <input type="text" size="30" name="q" id="q">
                <button class="submit btn btn-4 btn-4a"><i class="fas fa-search"></i></button>

                <div id="response"></div>

            </form>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="footer">
            <p class="a">
                <a class="home-link" href="/login"><i class="fas fa-sign-in-alt"></i> Login</a>
                <a class="home-link" href="/vendors"><i class="fas fa-shopping-cart"></i> Vendors</a>
                <a class="home-link" href="/help"><i class="fas fa-question-circle"></i> Help</a>
            </p>
        </div>
    </div>
</div>
<div class="clear"></div>
</body>
</html>
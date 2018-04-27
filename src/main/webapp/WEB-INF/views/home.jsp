<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Probe | Find your favorite smartphone and PC at the cheapest prices</title>
    <link href="css/home.css" rel="stylesheet" type="text/css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Petit+Formal+Script' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Alegreya+Sans:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300' rel='stylesheet' type='text/css'>
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <div class="wrap">
        <div class="content-grid">
            <p><img src="images/top.png" title=""></p>
        </div>
        <div class="grid">
            <p><img class="main_logo" src="images/coming.png" title=""></p>

            <form action="search" method="post">
                <input type="text" size="30" value="What are you looking for?" onblur="if (this.value=='') this.value = 'What are you looking for?'" onfocus="if (this.value=='What are you looking for?') this.value = ''" name="query" id="query">
                <button class="submit btn btn-4 btn-4a"><i class="fas fa-search"></i></button>

                <div id="response"></div>

            </form>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="footer">
            <p class="a"><a class="home-link" href="/login"><i class="fas fa-sign-in-alt"></i> Login</a><a class="home-link" href="/vendors"><i class="fas fa-list-alt"></i> Vendors</a></p>
        </div>
    </div>
</div>
<div class="clear"></div>
</body>
</html>
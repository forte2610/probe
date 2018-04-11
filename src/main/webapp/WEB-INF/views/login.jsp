<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login to Probe</title>
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
<form:form action="registration" method="get">
    <button class="btn btn-md btn-warning btn-block" type="Submit">Registration</button>
</form:form>

<div class="container">
    <img src="images/coming.jpg" class="img-fluid center-block" width="300" height="300" alt="Logo" />
    <form action="login" method="POST" class="form-signin">
        <h3 class="form-signin-heading" text="Welcome"></h3>
        <br/>

        <input  type="text" id="username" name="username"  placeholder="Username"
               class="form-control" /> <br/>
        <input type="password"  placeholder="Password"
               id="password" name="password" class="form-control" /> <br />
        <button type="submit" class="btn btn-lg btn-primary btn-block" name="Login">Login</button>
    </form>
</div>
</body>
</html>

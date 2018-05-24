<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login to Helios</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
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
    <form action="login" method="POST" class="form-signin">
        <input  type="text" id="username" name="username"  placeholder="Username"
               class="form-control helios-textbox" /> <br/>
        <input type="password"  placeholder="Password"
               id="password" name="password" class="form-control helios-textbox" /> <br />
        <button type="submit" class="btn btn-lg btn-primary btn-block generic-button" name="Login">Login</button>
        <c:if test="${param.error}">
            <div class="security-message">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div>
        </c:if>
        <form:form action="registration" method="get">
            <button class="btn btn-md btn-warning btn-block register-button" type="Submit">Register</button>
        </form:form>
    </form>
</div>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" type="text/css" href="/css/registration.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<form:form action="login" method="get">
    <button class="btn btn-md btn-warning btn-block" type="Submit">Already have an account? Login</button>
</form:form>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <form:form autocomplete="off" action="registration"
                       modelAttribute="newUser" method="post" class="form-horizontal">
                <h2>Registration Form</h2>
                <div class="form-group">
                    <div class="col-sm-9">
                        <form:input type="text" path="username" placeholder="Username"
                               class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <form:input type="text" path="email" placeholder="Email"
                               class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <form:input type="password" path="password"
                               placeholder="Password" class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <input type="password" placeholder="Confirm password" class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                </div>

                <span>${successMessage}</span>

            </form:form>
        </div>
    </div>
</div>

</body>
</html>
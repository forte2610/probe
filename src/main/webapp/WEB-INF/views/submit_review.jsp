<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit review</title>
    <link rel="stylesheet" type="text/css" href="/css/registration.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <form:form action="/submit-review/${vendorid}" method="post"
                       modelAttribute="newReview" autocomplete="off"  class="form-horizontal">
                <h2>Submit review</h2>
                <div class="form-group">
                    <div class="col-sm-9">
                        <label for="score">Give this vendor a score:</label>
                        <form:input type="number" name="score" path="score" value="2.5"
                                    min="1" max="5" step="0.5" class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <label for="content">Leave a comment:</label>
                        <form:textarea type="text" rows="5" name="content" path="content" placeholder="Content"
                                    class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <button type="submit" class="btn btn-primary btn-block">Submit review</button>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>

</body>
</html>
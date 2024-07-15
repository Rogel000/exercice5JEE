<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Dog</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-body bg-dark text-light rounded">
                    <h3 class="card-title">- View a Dog -</h3>
                    <hr>
                    <form>
                        <c:choose>
                            <c:when test="${not empty dog}">
                                <div class="form-group">
                                    <label for="name">Name:</label>
                                    <input type="text" class="form-control" id="name" name="name" value="${dog.name}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="breed">Breed:</label>
                                    <input type="text" class="form-control" id="breed" name="breed" value="${dog.breed}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="birthdate">Birth date:</label>
                                    <input type="text" class="form-control" id="birthdate" name="birthdate" value="${dog.birthDate}" readonly>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p>Dog not found.</p>
                            </c:otherwise>
                        </c:choose>
                        <hr>
                        <div class="d-flex justify-content-end">
                            <button type="button" class="btn border border-success text-success" onclick="window.history.back();">
                                <i class="bi bi-arrow-left-circle"></i> Return
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>

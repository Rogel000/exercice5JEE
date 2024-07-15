<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Add a dog</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-body bg-dark text-light rounded">
                    <h3 class="card-title">- Add a Dog -</h3>
                    <hr>
                    <form method="post" action="${pageContext.request.contextPath}/add">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Enter dog's name" required>
                        </div>
                        <div class="form-group">
                            <label for="breed">Breed:</label>
                            <input type="text" class="form-control" id="breed" name="breed" placeholder="Enter dog's breed" required>
                        </div>
                        <div class="form-group">
                            <label for="birthdate">Birth date:</label>
                            <input type="date" class="form-control" id="birthdate" name="birthdate" required>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn border border-success text-success">
                                <i class="bi bi-plus-circle"></i> Add Dog
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

<jsp:useBean id="dogs" type="java.util.ArrayList<org.example.exercice5jee.entity.Dog>" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Dog List</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mt-5">
                <div class="card-body bg-dark text-light rounded">
                    <h3 class="card-title">- Dogs List -</h3>
                    <hr>
                    <% if (!dogs.isEmpty()) { %>
                    <table class="table table-dark mt-4">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Breed</th>
                            <th>Birth date</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < dogs.size(); i++) { %>
                        <tr>
                            <td><%= i  %>
                            </td>
                            <td><%= dogs.get(i).getName() %>
                            </td>
                            <td><%= dogs.get(i).getBreed() %>
                            </td>
                            <td><%= dogs.get(i).getBirthDate() %>
                            </td>
                            <td>
                                <a href="<%= request.getContextPath() %>/dogs/view?id=<%= dogs.get(i).getId() %>" class="btn btn-info btn-sm">
                                    <i class="bi bi-eye"></i> Details
                                </a>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } else { %>
                    <p>There is no dog in the database yet!</p>
                    <% } %>
                    <hr>
                    <div class="d-flex justify-content-end mt-3">
                        <a href="add" class="btn border border-success text-success">
                            <i class="bi bi-plus-circle"></i> Add a Dog
                        </a>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</body>
</html>

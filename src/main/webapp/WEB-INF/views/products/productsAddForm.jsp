<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-5">
            <div class="card">
                <div class="card-body">
                    <form method="post" action="${pageContext.request.contextPath}/addProduct">
                        <p class="h4 text-center py-4">Add Product</p>
                        <div class="md-form">
                            <div class="md-form">
                                <i class="fa fa-file-signature prefix grey-text"></i>
                                <input name="name" type="text" id="1" class="form-control">
                                <label for="1" class="font-weight-light">Name</label>
                            </div>
                            <div class="md-form">
                                <i class="fa fa-dollar-sign prefix grey-text"></i>
                                <input name="price" type="text" id="2" class="form-control">
                                <label for="2" class="font-weight-light">Price</label>
                            </div>
                        </div>
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-cyan" type="submit">Add Product</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-5">
            <div class="card">
                <div class="card-body">

                    <form method="post" action="${pageContext.request.contextPath}/login">
                        <p class="h4 text-center py-4">Login</p>
                        <div class="md-form">
                            <i class="fa fa-sign-in-alt prefix grey-text"></i>
                            <input name="login" type="text" id="materialFormCardEmailEx" class="form-control">
                            <label for="materialFormCardEmailEx" class="font-weight-light">Login</label>
                        </div>

                        <div class="md-form">
                            <i class="fa fa-lock prefix grey-text"></i>
                            <input name="pwd" type="password" id="materialFormCardConfirmEx" class="form-control">
                            <label for="materialFormCardConfirmEx" class="font-weight-light">Password</label>
                        </div>

                        <div class="my-2 text-center">
                            <div class="d-inline invalid-feedback">
                                ${errorMsg}
                            </div>
                        </div>
                        <div class="text-center py-4 mt-3">
                            <button class="btn btn-cyan" type="submit">Login</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h1>Please provide your product details</h1>

<form method="post" action="${pageContext.request.contextPath}/addProduct">
    <ul>
        <li> Input product name <input type="text" name="name"> </li>
        <li> Input product price <input type="text" name="price"> </li>
        <li> <button type="submit">Add Product</button> </li>
    </ul>
</form>
</body>
</html>
<jsp:include page="../include/footer.jsp"></jsp:include>

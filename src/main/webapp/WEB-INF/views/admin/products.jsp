<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>All products page</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value ="${product.getId()}"/>
            </td>
            <td>
                <c:out value ="${product.name}"/>
            </td>
            <td>
                <c:out value ="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/products/delete?id=${product.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    <td>
        <a href="${pageContext.request.contextPath}/addProduct">Add Products</a>
    </td>
</table>
</body>
</html>
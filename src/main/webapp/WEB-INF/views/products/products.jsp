<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"></jsp:include>
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
        <th>Add</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value ="${product.id}"/>
            </td>
            <td>
                <c:out value ="${product.name}"/>
            </td>
            <td>
                <c:out value ="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/products/addToCart?id=${product.id}">Add</a>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../include/footer.jsp"></jsp:include>

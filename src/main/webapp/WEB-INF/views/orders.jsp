<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
<h1>All Orders</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Users</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value ="${order.getId()}"/>
            </td>
            <td>
                <c:out value ="${order.getUser().getName()}"/>
            </td>
            <td>
                <a class="btn" href="${pageContext.request.contextPath}/order/detail?id=${order.getId()}">Details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/delete?id=${order.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<jsp:include page="include/footer.jsp"></jsp:include>

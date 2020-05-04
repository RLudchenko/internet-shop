<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>Order Number ${id}</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.getId()}"/>
            </td>
            <td>
                <c:out value="${product.getName()}"/>
            </td>
            <td>
                <c:out value="${product.getPrice()}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</form>
</body>
</html>
<jsp:include page="include/footer.jsp"></jsp:include>

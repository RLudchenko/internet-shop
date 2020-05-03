<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
<table class="table">
    <thead class="black white-text">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Login</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value ="${user.getId()}"/>
            </td>
            <td>
                <c:out value ="${user.name}"/>
            </td>
            <td>
                <c:out value ="${user.getLogin()}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/delete?id=${user.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>

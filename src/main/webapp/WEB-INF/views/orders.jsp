<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-7">
            <table class="table">
                <thead class="cyan accent-3 white-text">
                <tr>
                    <th>ID</th>
                    <th>Users</th>
                    <th>Details</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>
                            <c:out value ="${order.getId()}"/>
                        </td>
                        <td>
                            <c:out value ="${order.getUser().getName()}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/order/detail?id=${order.getId()}">Details</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/order/delete?id=${order.getId()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>

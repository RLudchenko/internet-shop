<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="purple white-text">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Add</th>
                </tr>
                </thead>
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
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>

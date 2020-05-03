<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="purple white-text">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Delete</th>
                </tr>
                </thead>
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
                            <a href="${pageContext.request.contextPath}/cart/delete?id=${product.getId()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a сlass="btn btn-info btn-block my-4 float-right" href="${pageContext.request.contextPath}/order">Complete Order</a>
            </form>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>

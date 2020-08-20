<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="px-4 py-4">
    <h4 class="text-center"><spring:message code="label.orders.navbar" text="default"/></h4>    

    <%@include file="/WEB-INF/pages/partials/messages.jsp" %>

    <div class="table-responsive-md">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="column.datetime" text="default"/></th>
                    <th scope="col"><spring:message code="column.name" text="default"/></th>
                    <th scope="col"><spring:message code="column.city" text="default"/></th>
                    <th scope="col"><spring:message code="column.address" text="default"/></th>
                    <th scope="col"><spring:message code="column.phone" text="default"/></th>
                    <th scope="col"><spring:message code="column.products" text="default"/></th>
                    <th scope="col"><spring:message code="column.total" text="default"/></th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <c:choose>
                    <c:when test="${orders != null && !orders.isEmpty()}">
                        <c:forEach items="${orders}" var="order" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${order.createdAt}</td>
                                <td>${order.contactName}</td>
                                <td>${order.city}</td>
                                <td>${order.address}</td>
                                <td>${order.phone}</td>
                                <td>
                                    <c:forEach items="${order.items}" var="orderItem" varStatus="loop2">
                                        ${orderItem.product.name} X ${orderItem.quantity} =  ${orderItem.amount}
                                        <c:if test="${loop2.index < (order.items.size() - 1)}">
                                            <br>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="RSD "/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="9" class="text-center"></td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
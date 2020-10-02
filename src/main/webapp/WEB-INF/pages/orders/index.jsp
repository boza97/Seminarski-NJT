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
                    <th scope="col"><spring:message code="column.total" text="default"/></th>
                    <th scope="col"><spring:message code="column.action" text="default"/></th>
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
                                <td><fmt:formatNumber value="${order.total}" type="currency" currencySymbol="RSD "/></td>
                                <td>
                                    <a href="${root}/orders/${order.id}/view" class="btn btn-primary">
                                        <spring:message code="button.details" text="default"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="9" class="text-center">
                                <spring:message code="label.orders.empty" text="default"/>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
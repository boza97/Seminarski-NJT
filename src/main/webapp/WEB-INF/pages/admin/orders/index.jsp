<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="px-4 py-4">
    <h4 class="text-center mb-4">
        <spring:message code="label.orders.navbar" text="default"/>
    </h4>    

    <%@include file="/WEB-INF/pages/partials/messages.jsp" %>

    <div class="mb-3">
        <div class="row">
            <div class="col-md-3 ml-auto">
                <form action="#" method="GET">
                    <input type="text" class="form-control" id="search" name="search" 
                           placeholder="<spring:message code="placeholder.search" text="default" />"
                           value="${search}">
                </form>
            </div>
        </div>
    </div>

    <div class="table-responsive-md">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col"><spring:message code="column.order.id" text="default"/></th>
                    <th scope="col"><spring:message code="column.datetime" text="default"/></th>
                    <th scope="col"><spring:message code="column.name" text="default"/></th>
                    <th scope="col"><spring:message code="column.total" text="default"/></th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody id="tableBody">

                <c:choose>
                    <c:when test="${not empty orders}">
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.createdAt}</td>
                                <td>${order.contactName}</td>
                                <td>${order.total}</td>
                                <td>${order.status}</td>
                                <td>
                                    <a href="${root}/admin/orders/${order.id}/view" class="btn btn-primary">
                                        <spring:message code="button.details" text="default"/>
                                    </a>
                                </td>
                            </tr> 
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="6" class="text-center">
                                <spring:message code="label.admin.orders.empty" text="default"/>
                            </td></tr>
                        </c:otherwise>
                    </c:choose>
            </tbody>
        </table>
    </div>
</div>


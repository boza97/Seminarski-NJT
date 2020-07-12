<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="px-4 py-4">
    <h4 class="text-center">Narudžbine</h4>    

    <%@include file="/WEB-INF/pages/partials/messages.jsp" %>

    <div class="table-responsive-md">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Rb</th>
                    <th scope="col">Datum</th>
                    <th scope="col">Ime i prezime</th>
                    <th scope="col">Grad</th>
                    <th scope="col">Adresa</th>
                    <th scope="col">Telefon</th>
                    <th scope="col">Proizvodi</th>
                    <th scope="col">Ukupno</th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <c:choose>
                    <c:when test="${orders != null && !orders.isEmpty()}">
                        <c:forEach items="${orders}" var="order" step="i">
                            <tr>
                                <td>${i + 1}</td>
                                <td>${order.createdAt}</td>
                                <td>${order.contactName}</td>
                                <td>${order.city}</td>
                                <td>${order.address}</td>
                                <td>${order.phone}</td>
                                <td>
                                    <c:forEach items="${order.items}" var="orderItem" step="j">
                                        ${orderItem.product.name + " X " + orderItem.quantity + " = " + orderItem.amount}
                                        <c:if test="${j < (order.items.size() - 1)}">
                                            <br>
                                        </c:if>
                                    </c:forEach>

                                </td>
                                <td>${order.total}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="9" class="text-center">Nemate ni jednu narudžbinu.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
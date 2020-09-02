<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="cart" value="${sessionScope.cart}" />

<div class="px-4 py-4">
    <h4 class="text-center">
        <spring:message code="heading.cart" text="default"/>
    </h4>

    <%@include file="/WEB-INF/pages/partials/messages.jsp" %>
    <form action="${root}/cart/add-quantities" method="POST"> 
        <div class="table-responsive-md">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col"><spring:message code="column.product" text="default"/></th>
                        <th scope="col"><spring:message code="column.price" text="default"/></th>
                        <th scope="col"><spring:message code="column.quantity" text="default"/></th>
                        <th scope="col"><spring:message code="column.total" text="default"/></th>
                        <th scope="col"><spring:message code="column.delete" text="default"/></th>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <c:choose>
                        <c:when test="${not empty cart}">
                            <c:forEach items="${cart}" var="orderItem">
                                <tr id="prod${orderItem.product.id}">
                                    <td>${orderItem.product.name}</td>
                                    <td id="price${orderItem.product.id}">RSD ${orderItem.product.price}</td>
                                    <td>
                                        <input type="hidden" name="productid[]" value="${orderItem.product.id}">
                                        <input class="form-control w-75" id="quantitiy" type="number" name="quantity[]" min='1' max='${orderItem.product.quantity}' value="1" onchange="calculateTotal(this, ${orderItem.product.id});">
                                    </td>
                                    <td id="total${orderItem.product.id}">RSD ${orderItem.product.price}</td>          
                                    <td><button type="button" class="btn btn-link p-0 text-danger" onclick="removeFromCart(${orderItem.product.id});">X</button></td>
                                </tr> 
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="text-center" colspan="5">
                                    <spring:message code="label.cart.empty" text="default"/>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <c:if test="${not empty cart}">
            <div id="btn-checkout" class="form-group text-right">
                <input class="btn btn-primary" type="submit" name="checkout" id="checkout" 
                       value="<spring:message code="button.cart.to.checkout" text="default"/>"
                       >
            </div> 
        </c:if>
    </form> 
</div>
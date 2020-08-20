<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<ul>
    <li><a href="${root}/home"><spring:message code="label.home.navbar" text="default"/></a></li>
    <li><a href="${root}/products"><spring:message code="label.products.navbar" text="default"/></a></li>
        <c:if test="${user != null}">
        <li><a href="cart.php"><spring:message code="label.cart.navbar" text="default"/></a></li>
        </c:if>
</ul>

<p>Copyright &copy; 2020 Btechnology</p>
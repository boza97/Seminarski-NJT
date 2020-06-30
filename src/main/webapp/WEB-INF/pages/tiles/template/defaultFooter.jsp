<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<ul>
    <li><a href="${root}/home">Početna</a></li>
    <li><a href="${root}/products">Proizvodi</a></li>
        <c:if test="${user != null}">
        <li><a href="cart.php">Korpa</a></li>
        </c:if>
</ul>

<p>Copyright &copy; 2020 Btechnology</p>
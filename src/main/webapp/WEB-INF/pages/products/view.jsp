<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="locale">${pageContext.response.locale}</c:set>

    <div class="container my-4">
        <div class="row">

            <div class="col-md-6 col-sm-12 border-right">
            <c:choose>
                <c:when test="${productDto.image != null}">
                    <img height="500" width="500" class="image-responsive" src="${root}/resources/img/products/${productDto.image}" alt="${productDto.image}">
                </c:when>
                <c:otherwise>
                    <img height="500" width="500" class="image-responsive" src="${root}/resources/img/products/no-image.png"> 
                </c:otherwise>
            </c:choose>

        </div>
        <div class="col-md-6 col-sm-12">      
            <h3 class="text-center">${productDto.name}</h3>

            <h5 class="mt-4 ">
                <span class="font-weight-bold">
                    <spring:message code="label.category" text="default"/>: 
                </span>
                ${productDto.category.name}
            </h5>

            <h5 >
                <span class="font-weight-bold"><spring:message code="label.product.price" text="default"/>: </span>
                <span class="text-danger">
                    <fmt:formatNumber value="${productDto.price}" type="currency" currencySymbol="RSD "/>
                </span>
            </h5>

            <h5 class="mb-0 font-weight-bold">
                <spring:message code="label.product.details" text="default"/>: 
            </h5>

            <p>${productDto.localizations[locale].description}</p>

            <div>
                <button class="btn btn-warning" onclick="addToCart(${productDto.id})">
                    <spring:message code="button.cart.add" text="default"/>
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/partials/cartMessageModal.jsp"%>
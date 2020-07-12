<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="row px-3" id="products">
    <c:forEach items="${products}" var="product">
        <div class="col-lg-4 col-md-6 col-sm-12 mt-5">
            <div class="card">
                <c:choose>
                    <c:when test="${product.image != null}">
                        <img class="card-img-top index-prod-image mx-auto my-3" src="${root}/resources/img/products/${product.image}" alt="${product.name}">
                    </c:when>
                    <c:otherwise>
                        <img class="card-img-top index-prod-image mx-auto my-3" src="${root}/resources/img/products/no-image.png"> 
                    </c:otherwise>
                </c:choose>
                <div class="card-body border-top">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text ">
                        <strong>Cena: </strong>
                        <span class="text-danger">${product.price}</span>
                    </p>
                    <a href="${root}/products/${product.id}/view" class="btn btn-primary">Detalji</a>
                    <button class="btn btn-warning mx-3" onclick="addToCart(${product.id})">Dodaj u korpu</button>
                </div>
            </div>  
        </div>
    </c:forEach>
</div>
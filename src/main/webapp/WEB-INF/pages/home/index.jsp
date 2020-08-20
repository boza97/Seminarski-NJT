<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="slider-wrapper theme-default">
    <div id="slider" class="nivoSlider">
        <img src="${root}/resources/img/slider/slide1.png" data-thumb="resources/img/slider/slide1.png">
        <img src="${root}/resources/img/slider/slide2.jpg" data-thumb="resources/img/slider/slide2.jng">
        <img src="${root}/resources/img/slider/slide3.jpg" data-thumb="resources/img/slider/slide3.jpg">
        <img src="${root}/resources/img/slider/slide4.jpg" data-thumb="resources/img/slider/slide4.jpg">
    </div>
</div>

<hr>

<h3 id="main-h2" class="text-center font-weight-bold mt-5"><spring:message code="label.home.featured" text="default"/></h3>

<div class="containter px-3">
    <div class="row mb-5">

        <c:forEach items="${featuredProducts}" var="product">
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
                            <strong><spring:message code="label.product.price" text="default"/>: </strong>
                            <span class="text-danger">
                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="RSD " /></span>
                        </p>
                        <a href="${root}/products/${product.id}/view" class="btn btn-primary">
                            <spring:message code="button.product.details" text="default"/>
                        </a>
                    </div>
                </div>  
            </div>
        </c:forEach>
    </div>
</div>
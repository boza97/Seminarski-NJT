<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="slider-wrapper theme-default">
    <div id="slider" class="nivoSlider">
        <img src="resources/img/slide1.png" data-thumb="resources/img/slide1.png">
        <img src="resources/img/slide2.jpg" data-thumb="resources/img/slide2.jng">
        <img src="resources/img/slide3.jpg" data-thumb="resources/img/slide3.jpg">
        <img src="resources/img/slide4.jpg" data-thumb="resources/img/slide4.jpg">
    </div>
</div>

<hr>

<h3 id="main-h2" class="text-center font-weight-bold mt-5">IZDVAJAMO IZ PONUDE</h3>

<div class="containter px-3">
    <div class="row mb-5">

        <c:forEach items="${featuredProducts}" var="product">
            <div class="col-lg-4 col-md-6 col-sm-12 mt-5">
                <div class="card">
                    <c:choose>
                        <c:when test="${product.image != null}">
                            <img class="card-img-top index-prod-image mx-auto my-3" src="resources/img/products/${product.image}" alt="${product.image}">
                        </c:when>
                        <c:otherwise>
                            <img class="card-img-top index-prod-image mx-auto my-3" src="resources/img/products/no-image.png"> 
                        </c:otherwise>
                    </c:choose>

                    <div class="card-body border-top">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text ">
                            <strong>Cena: </strong>
                            <span class="text-danger">${product.price}</span>
                        </p>
                        <a href="" class="btn btn-primary">Detalji</a>
                    </div>
                </div>  
            </div>
        </c:forEach>
    </div>
</div>
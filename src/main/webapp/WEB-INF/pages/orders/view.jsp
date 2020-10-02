<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="locale">${pageContext.response.locale}</c:set>

<div class="container my-4">
    <div class="text-center mb-3">
            <h4><spring:message code="label.order" text="default"/> ${order.id}</h4>
    </div>

    <div class="row form-group">
        <div class="col-md-2 col-sm-6">
            <label class="col-form-label font-weight-bold"><spring:message code="column.name" text="default"/>:</label>
        </div>        
        <div class="col-md-4 col-sm-6">
            <input class="form-control-plaintext" value="${order.contactName}" readonly/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-2 col-sm-6">
            <label class="col-form-label font-weight-bold"><spring:message code="column.datetime" text="default"/>:</label>
        </div>  
        <div class="col-md-4 col-sm-6">
            <input class="form-control-plaintext" value="${order.createdAt}" readonly/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-2 col-sm-6">
            <label class="col-form-label font-weight-bold"><spring:message code="column.city" text="default"/>:</label>
        </div>  
        <div class="col-md-4 col-sm-6">
            <input class="form-control-plaintext" value="${order.city}" readonly/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-2 col-sm-6">
            <label class="col-form-label font-weight-bold"><spring:message code="column.address" text="default"/>:</label>
        </div>  
        <div class="col-md-4 col-sm-6">
            <input class="form-control-plaintext" value="${order.address}" readonly/>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-2 col-sm-6">
            <label class="col-form-label font-weight-bold"><spring:message code="column.phone" text="default"/>:</label>
        </div>    
        <div class="col-md-4 col-sm-6">
            <input class="form-control-plaintext" value="${order.phone}" readonly/>
        </div>        
    </div>
        
        <hr>

    <div class="text-center my-3">
        <h5><spring:message code="label.order.products" text="default"/></h5>
    </div>

    <c:forEach items="${order.items}" var="orderItem">
        <br>
        <div class="row">
            <div class="col-md-4 border-right">
                <c:choose>
                    <c:when test="${orderItem.product.image != null}">
                        <img height="150" width="150" class="image-responsive" src="${root}/resources/img/products/${orderItem.product.image}" alt="${orderItem.product.image}">
                    </c:when>
                    <c:otherwise>
                        <img height="150" width="150" class="image-responsive" src="${root}/resources/img/products/no-image.png"> 
                    </c:otherwise>
                </c:choose>
            </div>    
            <div class="col-md-8">
                <p>
                    <spring:message code="label.product.name" text="default"/>
                    : ${orderItem.product.name}
                </p>

                <p>
                    <spring:message code="label.product.price" text="default"/>
                    : 
                    <fmt:formatNumber value="${orderItem.product.price}" type="currency" currencySymbol="RSD "/>
                </p>

                <p>
                    <spring:message code="column.quantity" text="default"/>
                    : ${orderItem.quantity}
                </p>
            </div>
        </div>
    </c:forEach>

    <div class="text-right">
        <h5><spring:message code="column.total" text="default"/>:
            <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="RSD "/></h5>
    </div>
</div>
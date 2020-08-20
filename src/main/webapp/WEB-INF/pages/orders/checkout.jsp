<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="w-50 mx-auto pt-5 mb-5">
    <form:form action="${root}/orders" method="POST" modelAttribute="orderDto">
        <h2 class="text-center mb-4"><spring:message code="label.checkout" text="default"/></h2>

        <div class="form-group row">
            <label for="contactName" class="col-md-4 col-form-label text-md-right">
                <spring:message code="column.name" text="default"/>:
            </label>
            <div class="col-md-6">
                <spring:bind path="contactName">
                    <form:input type="text" id="contactName" class="form-control ${status.error ? 'is-invalid' : ''}" path="contactName"/>
                    <span class="invalid-feedback" role="alert">
                        <strong><form:errors path="contactName"/></strong>
                    </span>
                </spring:bind>
            </div>
        </div>

        <div class="form-group row">
            <label for="city" class="col-md-4 col-form-label text-md-right">
                <spring:message code="column.city" text="default"/>
            </label>
            <div class="col-md-6">
                <spring:bind path="city">
                    <form:input type="text" id="city" class="form-control ${status.error ? 'is-invalid' : ''}" path="city"/>
                    <span class="invalid-feedback" role="alert">
                        <strong><form:errors path="city" /></strong>
                    </span>
                </spring:bind>
            </div>
        </div>

        <div class="form-group row"> 
            <label for="city" class="col-md-4 col-form-label text-md-right">
                <spring:message code="column.address" text="default"/>
            </label>
            <div class="col-md-6">
                <spring:bind path="address">
                    <form:input type="text" id="address" class="form-control ${status.error ? 'is-invalid' : ''}" path="address"/>
                    <span class="invalid-feedback" role="alert">
                        <strong><form:errors path="address" /></strong>
                    </span>
                </spring:bind>
            </div>
        </div>

        <div class="form-group row">  
            <label for="city" class="col-md-4 col-form-label text-md-right">
                <spring:message code="column.phone" text="default"/>
            </label>
            <div class="col-md-6">
                <spring:bind path="phone">
                    <form:input type="text" id="phone" class="form-control ${status.error ? 'is-invalid' : ''}" path="phone"/>
                    <span class="invalid-feedback" role="alert">
                        <strong><form:errors path="phone" /></strong>
                    </span>
                </spring:bind>
            </div>
        </div>

        <div class="form-group text-right">
            <input type="submit" name="order" class="btn btn-primary" value="<spring:message code="button.order" text="default"/>">
            <div class="invalid-feedback"></div>
        </div>
    </form:form>
</div>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="w-50 mx-auto pt-5 mb-5">
    <form:form action="${root}/orders" method="POST" modelAttribute="orderDto">
        <h2 class="text-center mb-4">Naručivanje</h2>

        <div class="form-group">
            <spring:bind path="contactName">
                <form:input type="text" id="contactName" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Ime i prezime" path="contactName"/>
                <span class="invalid-feedback" role="alert">
                    <strong><form:errors path="contactName"/></strong>
                </span>
            </spring:bind>
        </div>

        <div class="form-group">  
            <spring:bind path="city">
                <form:input type="text" id="city" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Grad" path="city"/>
                <span class="invalid-feedback" role="alert">
                    <strong><form:errors path="city" /></strong>
                </span>
            </spring:bind>
        </div>

        <div class="form-group"> 
            <spring:bind path="address">
                <form:input type="text" id="address" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Adresa" path="address"/>
                <span class="invalid-feedback" role="alert">
                    <strong><form:errors path="address" /></strong>
                </span>
            </spring:bind>
        </div>

        <div class="form-group">  
            <spring:bind path="phone">
                <form:input type="text" id="phone" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Telefon" path="phone"/>
                <span class="invalid-feedback" role="alert">
                    <strong><form:errors path="phone" /></strong>
                </span>
            </spring:bind>
        </div>

        <div class="form-group text-right">
            <input type="submit" name="order" class="btn btn-primary" value="Naruči">
            <div class="invalid-feedback"></div>
        </div>
    </form:form>
</div>
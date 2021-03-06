<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="container pt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <%@include file="/WEB-INF/pages/partials/messages.jsp" %>
            <div class="card">
                <div class="card-header h4 text-center"><spring:message code="label.register" text="default"/></div>
                <div class="card-body">
                    <form:form action="${root}/register" method="POST" modelAttribute="registerUserDto">
                        <div class="form-group row">
                            <label for="firstName" class="col-md-4 col-form-label text-md-right">
                                <spring:message code="label.firstname" text="default"/>:
                            </label>
                            <div class="col-md-6">
                                <spring:bind path="firstName">
                                    <form:input id="firstName" class="form-control ${status.error ? 'is-invalid' : ''}" type="text" path="firstName"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="firstName"/></strong>
                                    </span>
                                </spring:bind>                                
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="lastName" class="col-md-4 col-form-label text-md-right">
                                <spring:message code="label.lastname" text="default"/>:
                            </label>
                            <div class="col-md-6">
                                <spring:bind path="lastName">
                                    <form:input id="lastName" class="form-control ${status.error ? 'is-invalid' : ''}" type="text" path="lastName"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="lastName"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="email" class="col-md-4 col-form-label text-md-right">
                                <spring:message code="label.email" text="default"/>:
                            </label>
                            <div class="col-md-6">
                                <spring:bind path="email">
                                    <form:input id="email" class="form-control ${status.error ? 'is-invalid' : ''}" type="text" path="email"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="email"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-md-4 col-form-label text-md-right">
                                <spring:message code="label.password" text="default"/>:
                            </label>
                            <div class="col-md-6">
                                <spring:bind path="password">
                                    <form:input id="password" type="password" class="form-control ${status.error ? 'is-invalid' : ''}" path="password"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="password"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password-confirm" class="col-md-4 col-form-label text-md-right">
                                <spring:message code="label.confirm.password" text="default"/>:
                            </label>
                            <div class="col-md-6">
                                <spring:bind path="confirmPassword">
                                    <form:input id="confirm-password" type="password" class="form-control ${status.error ? 'is-invalid' : ''}" path="confirmPassword"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="confirmPassword"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="button.register" text="default"/>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="container pt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header h4 text-center">Registracija</div>
                <div class="card-body">
                    <form:form action="${root}/register" method="POST" modelAttribute="userDto">
                        <div class="form-group row">
                            <label for="firstName" class="col-md-4 col-form-label text-md-right">Ime:</label>
                            <div class="col-md-6">
                                <spring:bind path="firstName">
                                    <form:input id="firstName" class="form-control ${status.error ? 'is-invalid' : ''}" path="firstName"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="firstName"/></strong>
                                    </span>
                                </spring:bind>                                
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="lastName" class="col-md-4 col-form-label text-md-right">Prezime:</label>
                            <div class="col-md-6">
                                <spring:bind path="lastName">
                                    <form:input id="lastName" class="form-control ${status.error ? 'is-invalid' : ''}" path="lastName"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="lastName"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="email" class="col-md-4 col-form-label text-md-right">E-mail adresa:</label>
                            <div class="col-md-6">
                                <spring:bind path="email">
                                    <form:input id="email" class="form-control ${status.error ? 'is-invalid' : ''}" path="email"/>
                                    <span class="invalid-feedback" role="alert">
                                        <strong><form:errors path="email"/></strong>
                                    </span>
                                </spring:bind>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-md-4 col-form-label text-md-right">Lozinka:</label>
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
                            <label for="password-confirm" class="col-md-4 col-form-label text-md-right">Potvrdite lozinku:</label>
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
                                    Registruj se
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
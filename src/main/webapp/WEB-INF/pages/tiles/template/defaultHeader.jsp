<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="locale" value="${pageContext.response.locale}" />

<nav class="navbar navbar-expand-lg color-primary">
    <a class="navbar-brand" href="${root}/home"><img src="${root}/resources/img/logo.png" height="40"></a>
    <button class="navbar-dark navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${root}/home"><spring:message code="label.home.navbar" text="default"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${root}/products"><spring:message code="label.products.navbar" text="default"/></a>
            </li>

            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${root}/cart"><spring:message code="label.cart.navbar" text="default"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${root}/orders"><spring:message code="label.orders.navbar" text="default"/></a>
                </li>
            </c:if>
        </ul>       

        <ul class="navbar-nav">
            <c:choose>
                <c:when test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link text-white"><spring:message code="label.header.greeting" text="default"/> 
                            <strong class="text-warning">${user.firstName}</strong></a>
                    </li>
                    <li class="nav-item">
                        <form action="${root}/logout" method="POST" id="logout">
                            <a class="nav-link" type="submit" onclick="document.getElementById('logout').submit();">
                                <spring:message code="label.logout" text="default"/>
                            </a>
                        </form>
                    </li> 
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/register">
                            <spring:message code="label.register.navbar" text="default"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/login">
                            <spring:message code="label.login.navbar" text="default"/>
                        </a>
                    </li>
                </c:otherwise>                    
            </c:choose>
            <li class="nav-item">
                <a class="nav-link" href="?lang=${ locale == 'sr' ? 'en_GB' : 'sr'}">
                    <img src="${root}/resources/img/languages/${locale == 'sr' ? 'en_GB' : 'sr'}-flag.png" title="<spring:message code="title.lang.image" text="default"/>"/>
                </a>
            </li>
        </ul>
    </div>
</nav>
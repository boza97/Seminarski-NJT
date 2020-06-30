<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-expand-lg color-primary">
    <a class="navbar-brand" href="${root}/home"><img src="${root}/resources/img/logo.png" height="40"></a>
    <button class="navbar-dark navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${root}/home">Početna</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${root}/products">Proizvodi</a>
            </li>

            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${root}/cart">Korpa</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${root}/orders">Narudžbine</a>
                </li>
            </c:if>

            <li class="nav-item">
                <a class="nav-link" href="${root}/news">Vesti</a>
            </li>
        </ul>       

        <ul class="navbar-nav">
            <c:choose>
                <c:when test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link text-white">Zdravo <strong class="text-warning">${user.firstName}</strong></a>
                    </li>
                    <li class="nav-item">
                        <form action="${root}/logout" method="POST" id="logout">
                            <a class="nav-link" type="submit" onclick="document.getElementById('logout').submit();">Odjavi se</a>
                        </form>
                    </li> 
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/register">Registruj se</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${root}/login">Prijavi se</a>
                    </li>
                </c:otherwise>                    
            </c:choose>
        </ul>
    </div>
</nav>
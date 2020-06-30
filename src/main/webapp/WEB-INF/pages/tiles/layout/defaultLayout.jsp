<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<tilesx:useAttribute id="cssList" name="stackCss" classname="java.util.List" />
<tilesx:useAttribute id="scriptList" name="scripts" classname="java.util.List" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title"></tiles:getAsString></title>

        <link rel='stylesheet' href='${root}/webjars/bootstrap/4.4.1/css/bootstrap.min.css'>        
        <link rel="stylesheet" href="${root}/resources/css/app.css">
        <c:forEach var="cssItem" items="${cssList}">
            <link rel="stylesheet" href="${root}/${cssItem}">        
        </c:forEach>
            
    </head>
    <body class="h-100">
        <div class="wraper">
            <header>
                <tiles:insertAttribute name="header"/>
            </header>

            <section id="content">
                <div class="main">
                    <tiles:insertAttribute name="body"/>
                </div>
            </section>

            <footer class="p-4">
                <tiles:insertAttribute name="footer"/>
            </footer>
        </div>

        <script type="text/javascript" src="${root}/webjars/jquery/3.1.1/jquery.min.js"></script>            
        <script type="text/javascript" src="${root}/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${root}/webjars/popper.js/1.16.0/umd/popper.min.js"></script>
        <c:forEach var="script" items="${scriptList}">
            <script type="text/javascript" src="${root}/${script}"></script>            
        </c:forEach>
    </body>
</html>

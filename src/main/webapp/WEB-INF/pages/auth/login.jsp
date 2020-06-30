<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="container pt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <%@include file="/WEB-INF/pages/partials/messages.jsp" %>
            <div class="card">
                <div class="card-header text-center h4">Prijavljivanje</div>

                <div class="card-body">
                    <form method="POST" action="${root}/login">

                        <div class="form-group row">
                            <label for="email" class="col-md-4 col-form-label text-md-right">E-mail adresa:</label>

                            <div class="col-md-6">
                                <input id="email" type="text" name="email" class="form-control" autofocus>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-md-4 col-form-label text-md-right">Lozinka:</label>

                            <div class="col-md-6">
                                <input id="password" name="password" type="password" class="form-control" autofocus>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Prijavi se
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

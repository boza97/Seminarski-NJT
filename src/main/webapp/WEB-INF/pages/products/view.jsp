<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="container my-4">
    <div class="row">

        <div class="col-md-6 col-sm-12 border-right">
            <c:choose>
                <c:when test="${productDto.image != null}">
                    <img height="500" width="500" class="image-responsive" src="${root}/resources/img/products/${productDto.image}" alt="${productDto.image}">
                </c:when>
                <c:otherwise>
                    <img height="500" width="500" class="image-responsive" src="${root}/resources/img/products/no-image.png"> 
                </c:otherwise>
            </c:choose>

        </div>
        <div class="col-md-6 col-sm-12">      
            <h3 class="text-center">${productDto.name}</h3>
            <h5 class="mt-4 ">
                <span class="font-weight-bold"> Kategorija: </span>
                ${productDto.category.name}
            </h5>
            <h5 >
                <span class="font-weight-bold">Cena: </span>
                <span class="text-danger">${productDto.price}</span>
            </h5>
            <h5 class="mb-0 font-weight-bold">Karakteristike: </h5>
            <p>${productDto.details}</p>

            <div>
                <button class="btn btn-warning" onclick="addToCart(${productDto.id})">Dodaj u korpu</button>
            </div>
        </div>
    </div>
</div>

<!-- Message modal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="messageModalLabel">Korpa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modalMsg"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
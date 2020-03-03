sucesso.jsp ---------

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ingresso:template>
    <jsp:body>
   		<div class=" col-md-6 col-md-offset-3">
        	<h1>Sucesso, sua compra foi realizada!</h1>
        	
        	<table class="table table-hover ">
                <thead>
		                <th>Sala</th>
		                <th>Lugar</th>
		                <th>Filme</th>
		                <th>Horario</th>
		                <th>Tipo do Ingresso</th>
		                <th>Preço</th>
                </thead>

                <tbody>
                <c:forEach items="${ingressos}" var="ingresso" varStatus="status">

                    <input type="hidden" name="ingressos[${status.index}].sessao.id" value="${ingresso.sessao.id}">
                    <input type="hidden" name="ingressos[${status.index}].lugar.id" value="${ingresso.lugar.id}">
                    <input type="hidden" name="ingressos[${status.index}].tipoDeIngresso" value="${ingresso.tipoDeIngresso}">
                    <tr>
                        <td>${ingresso.sessao.sala.nome}</td>
                        <td>${ingresso.lugar.fileira}${ingresso.lugar.posicao}</td>
                        <td>${ingresso.sessao.filme.nome }</td>
                        <td>${ingresso.sessao.horario}</td>
                        <td>${ingresso.tipoDeIngresso.descricao}</td>
                        <td>${ingresso.preco}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

		</div>
    </jsp:body>
</ingresso:template>
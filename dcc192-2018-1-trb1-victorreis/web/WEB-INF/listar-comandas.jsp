<%@page 
    import="java.util.Date, br.ufjf.dcc192.Comanda" 
    errorPage="pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="jspf/cabecalho.jspf" %>

<table border="1">
    <thead>
        <tr>
            <th>Id Comanda</th>
            <th>Mesa</th>
            <th>Responsável</th>
            <th>Hora de Abertura</th>
            <th>Hora de Fechamento</th>
            <th>Listar Itens</th>
            <th>Fechar/REABRIR Comanda</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${comandas}" var="comanda">
        <tr>
            <td>${comanda.getId()}</td>
            <td>${comanda.getMesa().getNome()}</td>
            <td>${comanda.getNomeClienteResponsavel()}</td>
            <td>${comanda.getHoraAbertura()}</td>
            <td>${comanda.getHoraFechamento()!=null?comanda.getHoraFechamento():""}</td>
            <td><a href="listar-itens-comanda.html?id=${comanda.getId()}">Listar</a></td>
            <td><a href="fechar-comanda.html?id=${comanda.getId()}">${comanda.getHoraFechamento() == null ? "Fechar" : "REABRIR"}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<%@include file="jspf/rodape.jspf" %>

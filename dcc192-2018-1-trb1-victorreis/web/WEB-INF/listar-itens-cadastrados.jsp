<%@page 
    import="java.util.Date, br.ufjf.dcc192.Item" 
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
            <th>Id Item</th>
            <th>Nome Item</th>
            <th>Valor Unitário</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${itens}" var="item">
        <tr>
            <td>${item.getId()}</td>
            <td>${item.getNome()}</td>
            <td>${item.getValor()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="jspf/rodape.jspf" %>

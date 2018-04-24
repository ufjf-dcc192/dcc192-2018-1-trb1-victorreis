<%@page 
    import="java.util.Date, br.ufjf.dcc192.Mesa" 
    errorPage="pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="jspf/cabecalho.jspf" %>

<form method="POST">
    <label for="nome-cliente">Nome do Cliente</label><br>
    <input type="text" name="nome-cliente" /><br>
    
    <label for="mesa">Selecionar Mesa</label><br>
    <select name="mesa">
        <c:forEach items="${mesas}" var="mesa">
            <option value="${mesa.getId()}">${mesa.getNome()}</option>
        </c:forEach>
    </select><br><br>
    
    <input type="submit" value="Cadastrar">
</form>

<%@include file="jspf/rodape.jspf" %>

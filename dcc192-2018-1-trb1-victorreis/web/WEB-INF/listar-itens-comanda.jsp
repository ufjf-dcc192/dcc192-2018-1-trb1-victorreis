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
            <th>Id Item</th>
            <th>Quantidade</th>
            <th>Nome Item</th>
            <th>Valor Unitário</th>
            <th>Valor TOTAL</th>
            <c:if test="${comanda.getHoraFechamento() == null}">
                <th>Excluir itens</th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${comanda.getItens()}" var="item">
            <tr>
                <td>${item.key.getId()}</td>
                <td>${item.value}</td>
                <td>${item.key.getNome()}</td>
                <td>${item.key.getValor()}</td>
                <td>${item.value*item.key.getValor()}</td>
                <c:if test="${comanda.getHoraFechamento() == null}">
                    <td><a href="remover-itens.html?id=${comanda.getId()}&id-item=${item.key.getId()}">Excluir</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>

<c:if test="${comanda.getHoraFechamento() == null}">
    <h2>Adicionar itens à comanda</h2>
    <form method="POST">
        <label for="item">Selecionar Item</label><br>
        <select name="item">
            <c:forEach items="${itens}" var="item">
                <option value="${item.getId()}">${item.getNome()} (R$${item.getValor()})</option>
            </c:forEach>
        </select><br>

        <label for="quantidade">Quantidade</label><br>
        <input type="number" name="quantidade" value="1" /><br>
        <p style="color:red">${erro}</p><br>

        <input type="hidden" name="id-comanda" value="${comanda.getId()}">
        <input type="submit" value="Incluir Itens">
    </form>
</c:if>

<%@include file="jspf/rodape.jspf" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Dettaglio Immobile – Agenzia Affitti</title>
    <meta charset="utf-8">
    <style>
        body  { font-family: Arial, sans-serif; margin: 20px; background: #f5f4f0; }
        h1    { background: #1a3c5e; color: #fff; padding: 12px 18px; margin: 0 0 16px; }
        table { border-collapse: collapse; width: 500px; background: #fff; }
        th    { background: #dde4ed; padding: 8px 12px; text-align: left; width: 160px; }
        td    { padding: 8px 12px; border-bottom: 1px solid #ddd; }
        .disponibile { color: green;  font-weight: bold; }
        .riservato   { color: orange; font-weight: bold; }
        .affittato   { color: red;    font-weight: bold; }
        .prezzo { font-size: 1.3em; font-weight: bold; color: #1a3c5e; }
        a     { color: #1a3c5e; }
        .err  { color: red; }
    </style>
</head>
<body>

    <h1>Dettaglio Immobile</h1>

    <p><a href="AgenziaAffitti?action=lista">&larr; Torna alla lista</a></p>

    <c:choose>
        <c:when test="${empty immobile}">
            <p class="err">Immobile non trovato.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="6" cellspacing="2">
                <tr><th>ID</th>           <td>${immobile.getId()}</td></tr>
                <tr><th>Titolo</th>       <td>${immobile.getTitolo()}</td></tr>
                <tr><th>Tipo</th>         <td>${immobile.getTipo()}</td></tr>
                <tr><th>Stato</th>        <td class="${immobile.getStato()}">${immobile.getStato()}</td></tr>
                <tr><th>Città</th>        <td>${immobile.getCitta()}</td></tr>
                <tr><th>Indirizzo</th>    <td>${immobile.getIndirizzo()}</td></tr>
                <tr><th>Prezzo / mese</th><td class="prezzo">€ ${immobile.getPrezzoMensile()}</td></tr>
                <tr><th>Superficie</th>   <td>${immobile.getMq()} mq</td></tr>
                <tr><th>Locali</th>       <td>${immobile.getLocali()}</td></tr>
                <tr><th>Piano</th>        <td>${immobile.getPiano()}</td></tr>
                <tr><th>Descrizione</th>  <td>${immobile.getDescrizione()}</td></tr>
            </table>
        </c:otherwise>
    </c:choose>

</body>
</html>
